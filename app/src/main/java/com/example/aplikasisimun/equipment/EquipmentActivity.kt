package com.example.aplikasisimun.equipment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasisimun.R
import com.example.aplikasisimun.adapter.ListEquipmentAdapter
import com.example.aplikasisimun.data.Education
import com.example.aplikasisimun.data.Equipment

class EquipmentActivity : AppCompatActivity() {
    
    private lateinit var rvEquipment: RecyclerView
    private val listEqu = ArrayList<Equipment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equipment)
        supportActionBar?.show()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        rvEquipment = findViewById(R.id.rv_equipment)
        rvEquipment.setHasFixedSize(true)
        listEqu.addAll(getListEquipment())
        showRecyclerList()
        
        
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_perlengkapan, menu)
        return true
    }

    private fun getListEquipment(): ArrayList<Equipment>{
        val namaEqu = resources.getStringArray(R.array.data_equ)
        val descEqu = resources.getStringArray(R.array.desc_equ)
        val descQonse = resources.getStringArray(R.array.consequence)
        val descSolution = resources.getStringArray(R.array.solution)
        val photoEqu = resources.obtainTypedArray(R.array.photo_equ)
        val photoColor = resources.obtainTypedArray(R.array.photo_color)
        

        val listEquipment = ArrayList<Equipment>()
        for(i in namaEqu.indices){
            val equipment = Equipment(namaEqu[i], descEqu[i], descQonse[i], descSolution[i], photoEqu.getResourceId(i,-1), photoColor.getResourceId(i, -1))
            listEquipment.add(equipment)
        }
        return listEquipment
        
        
    }

    private fun showRecyclerList(){
        rvEquipment.layoutManager = LinearLayoutManager(this)
        val listEquipmentAdapter = ListEquipmentAdapter(listEqu)
        rvEquipment.adapter = listEquipmentAdapter
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.info_perlengkapan-> {
                val intent = Intent(this, RecommendedActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


    }
    
}