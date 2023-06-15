package com.example.aplikasisimun.equipment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.aplikasisimun.R

class RecommendedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommended)
        supportActionBar?.show()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // API 5+ solution
                onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}