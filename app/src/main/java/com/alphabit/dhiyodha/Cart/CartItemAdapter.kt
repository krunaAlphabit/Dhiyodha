package com.alphabit.dhiyodha.Cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alphabit.dhiyodha.databinding.RowCartItemBinding
import com.alphabit.dhiyodha.databinding.RowHomeCategoriesBinding

class CartItemAdapter : RecyclerView.Adapter<CartItemAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: RowCartItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RowCartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 10
    }
}