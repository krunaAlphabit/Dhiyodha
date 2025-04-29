package com.alphabit.dhiyodha.Dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.alphabit.dhiyodha.R
import com.alphabit.dhiyodha.databinding.FragmentCategoriesBinding

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerview()
    }

    private fun setUpRecyclerview() {
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        val categories = listOf(
            DashboardCategoriesModel("New Season", R.drawable.ic_new_season),
            DashboardCategoriesModel("Women", R.drawable.ic_new_season),
            DashboardCategoriesModel("Men", R.drawable.ic_new_season),
            DashboardCategoriesModel("Kids", R.drawable.ic_new_season),
            DashboardCategoriesModel("Winterwear", R.drawable.ic_new_season),
            DashboardCategoriesModel("Footwear", R.drawable.ic_new_season),
            DashboardCategoriesModel("Home & Life Style", R.drawable.ic_new_season),
            DashboardCategoriesModel("Jewellery", R.drawable.ic_new_season),
            DashboardCategoriesModel("Accessories", R.drawable.ic_new_season),
            DashboardCategoriesModel("Beauty", R.drawable.ic_new_season),
            DashboardCategoriesModel("Watches", R.drawable.ic_new_season),
            DashboardCategoriesModel("Perfume", R.drawable.ic_new_season)
        )
        binding.recyclerView.adapter = DashboardCategoriesAdapter(context, categories)
    }
}