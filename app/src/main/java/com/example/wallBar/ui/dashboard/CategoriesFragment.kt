package com.example.wallBar.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wallBar.Adapter.categories_adapter
import com.example.wallBar.Adapter.colortone_adapter
import com.example.wallBar.Model.CategoriesData
import com.example.wallBar.Model.Colortone_Data
import com.example.wallBar.databinding.FragmentCategoriesBinding
import com.google.firebase.firestore.FirebaseFirestore

class CategoriesFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentCategoriesBinding? = null
    lateinit var db: FirebaseFirestore

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        db= FirebaseFirestore.getInstance()
        db.collection("CateGory").addSnapshotListener { value, error ->
            Log.e("debugger", error?.message.orEmpty())
           val catlist= arrayListOf<CategoriesData>()
            val catdata= value?.toObjects(CategoriesData::class.java)
            catlist.addAll(catdata!!)
            for(i in catlist)
            {
                Log.e("cccc","catlist is returning valid "+i)

            }
            binding.recyclerViewCategories.layoutManager = GridLayoutManager(requireContext(),3)
            binding.recyclerViewCategories.adapter = categories_adapter(requireContext(), catlist)

        }
         db.collection("Colortone").addSnapshotListener{ value, error ->
            Log.e("debugger", error?.message.orEmpty())
         val colortonelist= arrayListOf<Colortone_Data>()
          val coldata= value?.toObjects(Colortone_Data::class.java)
            colortonelist.addAll(coldata!!)
            for(i in colortonelist)
            {
                Log.e("cccc","Color Tone is returning valid "+i)
            }
            binding.recyclerViewColortone.layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL,true)
            binding.recyclerViewColortone.adapter = colortone_adapter(requireContext(),colortonelist)

        }
        return binding.root
    }
    }
