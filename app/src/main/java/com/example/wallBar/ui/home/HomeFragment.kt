package com.example.wallBar.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wallBar.Adapter.tc_adapter
import com.example.wallBar.Model.FirebaseData
import com.example.wallBar.databinding.FragmentHomeBinding
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    lateinit var db: FirebaseFirestore

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        db = FirebaseFirestore.getInstance()
        db.collection("Top Selections").addSnapshotListener { value, error ->
            val listbestofthemont = arrayListOf<FirebaseData>()
            val data = value?.toObjects(FirebaseData::class.java)
            listbestofthemont.addAll(data!!)
            binding.recyclerViewBom.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.recyclerViewBom.adapter = tc_adapter(requireContext(), listbestofthemont)
        }
        return binding.root
    }


}
