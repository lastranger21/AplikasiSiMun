package com.example.aplikasisimun.profile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.aplikasisimun.R
import com.example.aplikasisimun.ServiceUpload
import com.example.aplikasisimun.loginlogout.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream
import kotlin.contracts.contract


class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var button: Button
    private lateinit var user: FirebaseUser
    private lateinit var textView: TextView
    private lateinit var name: TextView
    private lateinit var photo: ImageView
    private lateinit var imageUri: Uri
    private lateinit var test: TextView
    private lateinit var upl:Button
    private lateinit var sharedPreferences:SharedPreferences
    var fl: Boolean=false
    var contract = registerForActivityResult(ActivityResultContracts.GetContent()) {
        imageUri=it!!
        photo.setImageURI(it)
        fl=true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.show()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        auth = FirebaseAuth.getInstance()
        button = findViewById(R.id.logout)
        textView = findViewById(R.id.details)
        name = findViewById(R.id.nama)
        photo = findViewById(R.id.fotoprofil)
        user = auth.currentUser!!
        upl = findViewById(R.id.upload)

        var link = "https://firebasestorage.googleapis.com/v0/b/project-capstone-c23-pr519.appspot.com/o/Google.png?alt=media"

        sharedPreferences=getSharedPreferences("appdata", MODE_PRIVATE)
        val savedValue = sharedPreferences.getBoolean("FL",false)

        if (user == null) {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val uri: Uri? = user.photoUrl
            if(savedValue||fl){
                link = "https://firebasestorage.googleapis.com/v0/b/project-capstone-c23-pr519.appspot.com/o/111?alt=media"
            }else if (uri != null) {
                link = uri.toString()
            }
            textView.text = user.email
            name.text = user.displayName
            Picasso.get().load(link).into(photo)
            Picasso.get()
                .load(link)
                .resize(150, 150)
                .centerCrop()
                .into(photo)
        }

        photo.setOnClickListener{
            contract.launch ("image/*")

        }
        upl.setOnClickListener{
            upload()
            val editor = sharedPreferences.edit()
            editor.putBoolean("FL", true)
            editor.apply()
            Toast.makeText(
                applicationContext,
                "Image Uploaded",
                Toast.LENGTH_SHORT
            ).show()
        }

        button.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            val editor = sharedPreferences.edit()
//            editor.putBoolean("FL", false)
//            editor.apply()
            finish()
        }
    }

    private fun upload() {
        val dir = applicationContext.filesDir
        var file = File(dir, "111")
        val inputStream=contentResolver.openInputStream(imageUri)
        val outputStream = FileOutputStream(file)
        inputStream!!.copyTo(outputStream)
        val requestBody = file.asRequestBody("image/png".toMediaTypeOrNull())
        val part=MultipartBody.Part.createFormData("image", file.name, requestBody)
        val retrofit =
            Retrofit.Builder().baseUrl("https://imgendpoint-tndqi5ipta-et.a.run.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ServiceUpload::class.java)


        CoroutineScope(Dispatchers.IO).launch {
            val response = retrofit.uploadImage(part)
            Log.d("Halo", response.toString())

        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}