package com.example.aplikasisimun

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.aplikasisimun.R
import com.example.aplikasisimun.data.Mountain

class DetailMountain : AppCompatActivity() {
    private lateinit var expand1: CardView
    private lateinit var expand2: CardView
    private lateinit var layout1: LinearLayout
    private lateinit var layout2: LinearLayout



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.show()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val dataMountain = intent.getParcelableExtra<Mountain>("key_mountain") as Mountain


        val tvDetailName: TextView = findViewById(R.id.tv_detail_name)
        val ivDetailPhoto: ImageView = findViewById(R.id.iv_detail_photo)
        val tvDetailLocation: TextView = findViewById(R.id.tv_detail_location)
        val tvDetailDescription:TextView = findViewById(R.id.tv_detail_description)
        val tvDetailTips:TextView = findViewById(R.id.tips)
        val tvDetailTrack:TextView = findViewById(R.id.track)

        layout1 = findViewById(R.id.layouts_tips)
        layout2= findViewById(R.id.layouts_track)
        expand1 = findViewById(R.id.expands_tips)
        expand2 = findViewById(R.id.expands_track)



        layout1.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        expand1.setOnClickListener{
            val v = if(tvDetailTips.visibility == View.GONE )  View.VISIBLE else View.GONE
            tvDetailTips.visibility = v

        }
        layout2.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        expand2.setOnClickListener{
            val x = if(tvDetailTrack.visibility == View.GONE ) View.VISIBLE else View.GONE
            tvDetailTrack.visibility = x


        }

        tvDetailTips.text = dataMountain.tips
        tvDetailTrack.text = dataMountain.track
        tvDetailDescription.text = dataMountain.description
        tvDetailName.text = dataMountain.name
        ivDetailPhoto.setImageResource(dataMountain.photo)
        tvDetailLocation.text = dataMountain.location


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