package com.example.wallBar.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wallBar.Adapter.categories_adapter
import com.example.wallBar.Adapter.tc_adapter
import com.example.wallBar.Model.CategoriesData
import com.example.wallBar.Model.FirebaseData
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
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    }
