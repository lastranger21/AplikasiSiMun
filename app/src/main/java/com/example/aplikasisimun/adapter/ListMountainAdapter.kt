package com.example.aplikasisimun.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasisimun.DetailMountain
import com.example.aplikasisimun.R
import com.example.aplikasisimun.data.Mountain

class ListMountainAdapter(private val listMountain:ArrayList<Mountain>): RecyclerView.Adapter<ListMountainAdapter.ListViewHolder>(){

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvMdpl: TextView = itemView.findViewById(R.id.tv_item_mdpl)
        val tvLocation: TextView = itemView.findViewById(R.id.tv_item_location)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_mountain, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listMountain.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val(name, description,location,track  ,mdpl,tips, photo) = listMountain[position]
        holder.tvName.text = name
        holder.tvMdpl.text = mdpl
        holder.tvLocation.text = location
        holder.imgPhoto.setImageResource(photo)

        holder.itemView.setOnClickListener{
            val intentDetail = Intent(holder.itemView.context, DetailMountain::class.java)
            intentDetail.putExtra("key_mountain", listMountain[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }
}