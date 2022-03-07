package com.example.wallBar.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallBar.Model.FeaturedData
import com.example.wallBar.Model.FirebaseData
import com.example.wallBar.R
import com.example.wallBar.ui.ClickedWallpaper

class FeaturedAdapter(val requireContext: Context, val listfeatured: ArrayList<FeaturedData>) :
    RecyclerView.Adapter<FeaturedAdapter.Featured_ViewHolder>() {
    inner class Featured_ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgvw1= itemView.findViewById<ImageView>(R.id.wpimage3)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Featured_ViewHolder {
        return Featured_ViewHolder(LayoutInflater.from(requireContext).inflate(R.layout.featured,parent,false))
    }

    override fun onBindViewHolder(holder: Featured_ViewHolder, position: Int) {
        holder.imgvw1
        Glide.with(requireContext).load(listfeatured[position].Link).into(holder.imgvw1);
        holder.itemView.setOnClickListener {
            val intent= Intent(requireContext,ClickedWallpaper::class.java)
            intent.putExtra("link",listfeatured[position].Link)
            requireContext.startActivity(intent)
        }

    }

    override fun getItemCount()=listfeatured.size
}