package com.example.wallBar.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallBar.Model.FirebaseData
import com.example.wallBar.R
import com.example.wallBar.ui.ClickedWallpaper

class tc_adapter( val requireContext: Context, val listbestofthemont: ArrayList<FirebaseData>) :RecyclerView.Adapter<tc_adapter.tc_viewholder>() {
    inner class tc_viewholder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val imgvw= itemView.findViewById<ImageView>(R.id.wpimage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): tc_viewholder {
      return tc_viewholder(
          LayoutInflater.from(requireContext).inflate(R.layout.topcollections,parent,false)
      )
    }

    override fun onBindViewHolder(holder: tc_viewholder, position: Int) {
        holder.imgvw
        Glide.with(requireContext).load(listbestofthemont[position].link).into(holder.imgvw);
        holder.itemView.setOnClickListener {
           val intent= Intent(requireContext,ClickedWallpaper::class.java)
            intent.putExtra("link",listbestofthemont[position].link)
            requireContext.startActivity(intent)
        }

    }

    override fun getItemCount()= listbestofthemont.size
}