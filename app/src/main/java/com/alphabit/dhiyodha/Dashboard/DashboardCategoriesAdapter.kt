package com.alphabit.dhiyodha.Dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alphabit.dhiyodha.databinding.RowHomeCategoriesBinding

class DashboardCategoriesAdapter : RecyclerView.Adapter<DashboardCategoriesAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: RowHomeCategoriesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RowHomeCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 10
    }
}