package com.example.wallBar.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallBar.Model.CategoriesData
import com.example.wallBar.Model.FeaturedData
import com.example.wallBar.R

class categories_adapter(val requireContext: Context, val catlist:ArrayList<CategoriesData>) :
RecyclerView.Adapter<categories_adapter.categories_viewholder>(){
inner class categories_viewholder(itemView: View):RecyclerView.ViewHolder(itemView){
    val imgvw2= itemView.findViewById<ImageView>(R.id.categoryimgview)
    val catname=itemView.findViewById<TextView>(R.id.nametext)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): categories_viewholder
    {
        return categories_viewholder(LayoutInflater.from(requireContext).inflate(R.layout.item_category,parent,false))
    }

    override fun onBindViewHolder(holder: categories_viewholder, position: Int)
    {
        holder.imgvw2
        holder.catname.text=catlist[position].Name
        Glide.with(requireContext).load(catlist[position].Link).into(holder.imgvw2);
    }

    override fun getItemCount()= catlist.size
}