package com.example.aplikasisimun.loginlogout

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.aplikasisimun.MainActivity
import com.example.aplikasisimun.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider


class LoginActivity : AppCompatActivity() {
    private lateinit var editTextEmail: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var buttonLogin: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var mAuth: FirebaseAuth
    private lateinit var textView: TextView
    private lateinit var inet: TextView
    private lateinit var googleSignIn: ImageView
    private lateinit var mSignClient: GoogleSignInClient
    private val REQ_ONE_TAP = 50

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser: FirebaseUser? = mAuth.currentUser
        if (currentUser != null) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
        editTextEmail = findViewById(R.id.email)
        editTextPassword = findViewById(R.id.password)
        buttonLogin = findViewById(R.id.btn_login)
        progressBar = findViewById(R.id.pr_bar)
        inet = findViewById(R.id.inetcon)
        textView = findViewById(R.id.register_now)
        googleSignIn = findViewById(R.id.ggl)
        inet.visibility = View.GONE

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .requestId()
            .build()
        mSignClient = GoogleSignIn.getClient(this, gso)



        //Check disini
        googleSignIn.setOnClickListener {

            val connectivityManager: ConnectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val network = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            val isConnected: Boolean = networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true

            if(isConnected){
                signIn()
            }else{
                Toast.makeText(this, "Please Check Your Internet", Toast.LENGTH_SHORT).show()
            }

        }

        textView.setOnClickListener {
            val intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        buttonLogin.setOnClickListener {
            val email: String = editTextEmail.text.toString()
            val password: String = editTextPassword.text.toString()
            progressBar.visibility = View.VISIBLE

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this@LoginActivity, "Please Enter Email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this@LoginActivity, "Please Enter Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    progressBar.visibility = View.GONE
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(
                            applicationContext,
                            "Authentication Successful.",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            this@LoginActivity,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun signIn() {
        val intent: Intent = mSignClient.signInIntent
        startActivityForResult(intent, REQ_ONE_TAP)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_ONE_TAP) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val acc: GoogleSignInAccount? = task.getResult(ApiException::class.java)
                if (acc != null) {
                    firebaseAuth(acc.idToken)
                }
            } catch (e: ApiException) {
                throw RuntimeException(e)
            }
        }
    }

    private fun firebaseAuth(idToken: String?) {
        val credential: AuthCredential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(
                        applicationContext,
                        "Authentication Successful.",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        this@LoginActivity,
                        "Authentication failed, Please Check Your Connectivity.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}