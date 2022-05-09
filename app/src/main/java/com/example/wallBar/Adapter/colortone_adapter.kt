package com.example.wallBar.Adapter

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color.parseColor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallBar.Model.CategoriesData
import com.example.wallBar.Model.Colortone_Data
import com.example.wallBar.R
import com.example.wallBar.ui.ClickedWallpaper
import com.google.firebase.database.collection.LLRBNode
import com.google.type.Color

class colortone_adapter(val requireContext: Context,val colortonelist:ArrayList<Colortone_Data>) :
    RecyclerView.Adapter<colortone_adapter.colortone_viewholder>(){
    inner class colortone_viewholder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tone1 = itemView.findViewById<CardView>(R.id.clor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): colortone_viewholder {
        return colortone_viewholder(LayoutInflater.from(requireContext).inflate(R.layout.colortonecollection,parent,false))
    }

    override fun onBindViewHolder(holder: colortone_viewholder, position: Int) {
        holder.tone1
        val intercol=colortonelist[position].Color
        holder.tone1.setCardBackgroundColor(android.graphics.Color.parseColor(intercol!!))
        holder.itemView.setOnClickListener {
            val intent= Intent(requireContext,ClickedWallpaper::class.java)
            intent.putExtra("link",colortonelist[position].link)
            requireContext.startActivity(intent)
        }
    }

    override fun getItemCount()=colortonelist.size
}