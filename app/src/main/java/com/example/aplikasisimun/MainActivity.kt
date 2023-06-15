package com.example.aplikasisimun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasisimun.adapter.ListMountainAdapter
import com.example.aplikasisimun.data.Mountain
import com.example.aplikasisimun.education.EducationActivity
import com.example.aplikasisimun.equipment.EquipmentActivity
import com.example.aplikasisimun.profile.ProfileActivity
import com.example.camera.CameraActivity

class MainActivity : AppCompatActivity() {

    private lateinit var rvMountain: RecyclerView
    private val list = ArrayList<Mountain>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ibEducation = findViewById<ImageButton>(R.id.ib_education)
        val ibEquipment = findViewById<ImageButton>(R.id.ib_equipment)
        val ibProfile = findViewById<ImageButton>(R.id.ib_profile)
        val ibCamera = findViewById<ImageButton>(R.id.ib_camera)

        rvMountain = findViewById(R.id.rv_mountain)


        rvMountain.setHasFixedSize(true)
        list.addAll(getListMountains())
        showRecyclerList()

        ibEducation.setOnClickListener {
            Intent(this, EducationActivity::class.java).also {
                startActivity(it)
            }
        }

        ibEquipment.setOnClickListener {
            Intent(this, EquipmentActivity::class.java).also {
                startActivity(it)
            }
        }
        ibProfile.setOnClickListener {
            Intent(this, ProfileActivity::class.java).also{
                startActivity(it)
            }
        }
        ibCamera.setOnClickListener {
            Intent(this, CameraActivity::class.java).also{
                startActivity(it)
            }
        }
    }

    private fun getListMountains(): ArrayList<Mountain>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataMdpl = resources.getStringArray(R.array.mdpl_name)
        val dataLocation = resources.getStringArray(R.array.location_name)
        val track = resources.getStringArray(R.array.track_mountain)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val tips = resources.getStringArray(R.array.tips_mountain)

        val listMountain = ArrayList<Mountain>()
        for (i in dataName.indices) {
            val mountain = Mountain( dataName[i], dataDescription[i], dataLocation[i], track[i], dataMdpl[i], tips[i],dataPhoto.getResourceId(i, -1))
            listMountain.add(mountain)
        }
        return listMountain
    }
    private fun showRecyclerList(){
        rvMountain.layoutManager = LinearLayoutManager(this)
        val listMountainAdapter = ListMountainAdapter(list)
        rvMountain.adapter = listMountainAdapter
    }

}