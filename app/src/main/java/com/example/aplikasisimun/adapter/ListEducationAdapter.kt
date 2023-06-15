package com.example.aplikasisimun.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasisimun.R
import com.example.aplikasisimun.data.Education
import com.example.aplikasisimun.education.DetailEducation


class ListEducationAdapter(private val listEducation:ArrayList<Education>): RecyclerView.Adapter<ListEducationAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val tvTitle: TextView = itemView.findViewById(R.id.tv_item_title)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_education, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listEducation.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val(title, description, photo) = listEducation[position]
        holder.tvTitle.text = title


        holder.itemView.setOnClickListener{
            val intentEducation = Intent(holder.itemView.context, DetailEducation::class.java)
            intentEducation.putExtra("key_education", listEducation[holder.adapterPosition])
            holder.itemView.context.startActivity(intentEducation)
        }
    }
}