package com.example.aplikasisimun.education

import android.animation.LayoutTransition
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.aplikasisimun.R
import com.example.aplikasisimun.data.Education
import com.example.aplikasisimun.data.Mountain

class DetailEducation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_education)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dataEducation = intent.getParcelableExtra<Education>("key_education") as Education

        val tvTitleName: TextView = findViewById(R.id.tv_detail_title)
        val tvDescriptionDetail: TextView = findViewById(R.id.tv_description_education)

        tvTitleName.text = dataEducation.title
        tvDescriptionDetail.text = dataEducation.description
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
