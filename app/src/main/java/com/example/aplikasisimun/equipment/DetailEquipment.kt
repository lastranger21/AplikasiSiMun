package com.example.aplikasisimun.equipment

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
import com.example.aplikasisimun.data.Education
import com.example.aplikasisimun.data.Equipment

class DetailEquipment : AppCompatActivity(){
    private lateinit var expand1: CardView
    private lateinit var expand2: CardView
    private lateinit var layout1: LinearLayout
    private lateinit var layout2: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_equipment)
        supportActionBar?.show()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dataEquipment = intent.getParcelableExtra<Education>("key_equipment") as Equipment

        val tvTitleName: TextView = findViewById(R.id.tv_name_equ)
        val tvDescriptionDetail: TextView = findViewById(R.id.tv_description_equipment)
        val ivDetailEquipment:ImageView = findViewById(R.id.iv_detail_equ)
        val tvConsequence: TextView = findViewById(R.id.consequence)
        val tvSolution: TextView = findViewById(R.id.solution)


        layout1 = findViewById(R.id.layouts_consequence)
        layout2= findViewById(R.id.layouts_solution)
        expand1 = findViewById(R.id.expands_consequence)
        expand2 = findViewById(R.id.expands_solution)


        layout1.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        expand1.setOnClickListener{
            val v = if(tvConsequence.visibility == View.GONE )  View.VISIBLE else View.GONE
            tvConsequence.visibility = v

        }
        layout2.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        expand2.setOnClickListener{
            val x = if(tvSolution.visibility == View.GONE ) View.VISIBLE else View.GONE
            tvSolution.visibility = x


        }
        tvConsequence.text = dataEquipment.consequence
        tvSolution.text = dataEquipment.solution
        tvTitleName.text = dataEquipment.name
        tvDescriptionDetail.text = dataEquipment.description
        ivDetailEquipment.setImageResource(dataEquipment.photoURL)


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
