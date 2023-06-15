package com.example.aplikasisimun.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.aplikasisimun.R
import com.example.aplikasisimun.data.Equipment
import com.example.aplikasisimun.equipment.DetailEquipment

class ListEquipmentAdapter(private val listEquipment:ArrayList<Equipment>): RecyclerView.Adapter<ListEquipmentAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameEqu: TextView = itemView.findViewById(R.id.tv_equipment_name)
        val photoEqu: ImageView = itemView.findViewById(R.id.img_equipment_photo)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_equipment,parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listEquipment.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val(name, description,consequence, solution, photoURL, photoColor) = listEquipment[position]
        holder.nameEqu.text = name
        holder.photoEqu.setImageResource(photoURL)

        holder.itemView.setOnClickListener{
            val intentEquipment = Intent(holder.itemView.context, DetailEquipment::class.java)
            intentEquipment.putExtra("key_equipment", listEquipment[holder.adapterPosition])
            holder.itemView.context.startActivity(intentEquipment)
        }
    }


}