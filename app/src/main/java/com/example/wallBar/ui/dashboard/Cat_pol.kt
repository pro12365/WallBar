package com.example.wallBar.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wallBar.Adapter.Catimageadapter
import com.example.wallBar.Adapter.categories_adapter
import com.example.wallBar.Adapter.tc_adapter
import com.example.wallBar.Model.CategoriesData
import com.example.wallBar.Model.TopCollectionData
import com.example.wallBar.R
import com.example.wallBar.databinding.ActivityCatPolBinding
import com.google.firebase.firestore.FirebaseFirestore

class cat_pol : AppCompatActivity() {
    lateinit var binding: ActivityCatPolBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding= ActivityCatPolBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.categoryrecycle.layoutManager
       val db= FirebaseFirestore.getInstance()
       val uid=intent.getStringExtra("Uid")
        val name= intent.getStringExtra("Name")
        val uid2=db.collection("D74fpLS5c6p6kwi4Y1bS").id
        Log.e("fuckuem",uid!!);
        db.collection("CateGory").document(uid!!).collection("Wallcat").addSnapshotListener { value, error ->
            Log.e("debugger", error?.message.orEmpty())
            val cate_gorylister= arrayListOf<TopCollectionData>()
            val cat_lister_data= value?.toObjects(TopCollectionData::class.java)
            cate_gorylister.addAll(cat_lister_data!!)
            binding.textcate.text=name.toString()
            binding.textcount.text="${cate_gorylister.size} Wallpaper Availabe"
            for(i in cate_gorylister)
            {
                Log.e("dddd","catlist is returning valid "+i)
            }
            binding.categoryrecycle.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            binding.categoryrecycle.adapter=Catimageadapter(this,cate_gorylister)
        }
    }
}