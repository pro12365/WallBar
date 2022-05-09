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
import com.example.wallBar.Adapter.FeaturedAdapter
import com.example.wallBar.Adapter.tc_adapter
import com.example.wallBar.Model.FeaturedData
import com.example.wallBar.Model.TopCollectionData
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
        db.collection("Featured").addSnapshotListener { value, error -> Log.e("Professional debugger",error?.message.orEmpty())
            val listfeatured= arrayListOf<FeaturedData>()
            val featdata= value?.toObjects(FeaturedData::class.java)
            listfeatured.addAll(featdata!!)
            for(i in listfeatured)
            {
                Log.e("tttt","reprting "+i)
            }
            binding.recyclerViewFeatured.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)
            binding.recyclerViewFeatured.adapter=FeaturedAdapter(requireContext(),listfeatured)
        }

        db.collection("Top Selections").addSnapshotListener { value, error ->
            Log.e("Professional debugger",error?.message.orEmpty())
            val listbestofthemont = arrayListOf<TopCollectionData>()
            val data = value?.toObjects(TopCollectionData::class.java)
            for(i in listbestofthemont)
            {
                Log.e("mmmm","reprting "+i)
            }

            if (!data.isNullOrEmpty())
            listbestofthemont.addAll(data!!)
            binding.recyclerViewBom.layoutManager = GridLayoutManager(requireContext(),2)
            binding.recyclerViewBom.adapter = tc_adapter(requireContext(), listbestofthemont)

        }

        return binding.root
    }


}
