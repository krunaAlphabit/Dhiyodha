package com.alphabit.dhiyodha.Wishlist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alphabit.dhiyodha.App.SessionManager
import com.alphabit.dhiyodha.Product.Product
import com.alphabit.dhiyodha.Product.ProductDetailsActivity
import com.alphabit.dhiyodha.Product.ProductListingActivity
import com.alphabit.dhiyodha.R
import com.alphabit.dhiyodha.databinding.ItemProductListingBinding
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView

class WishlistItemAdapter(
    private val context: Context,
    private val productsModel: MutableList<Product>,
) : RecyclerView.Adapter<WishlistItemAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ItemProductListingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.tvItemName
        val subTitle: TextView = binding.tvItemSubTitle
        val productOldPrice: TextView = binding.tvProductOldPrice
        val productNewPrice: TextView = binding.tvProductNewPrice
        val productPerOff: TextView = binding.tvProductPercentageOff
        val productDeliveryBy: TextView = binding.tvProductDeliveryBy
        val productImage: RoundedImageView = binding.imgProductImage

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemProductListingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model = productsModel[position]

        holder.title.text = model.productName
        holder.subTitle.text = model.description
        holder.productOldPrice.text = model.mrp
        holder.productNewPrice.text = model.listingPrice
        holder.productDeliveryBy.text = model.description
        Glide.with(context).load(model.imagesUrl[0].toString()).error(R.drawable.ic_category_girl)
            .placeholder(R.drawable.ic_category_girl).into(holder.productImage)

        holder.itemView.setOnClickListener {
            val i = Intent(context, ProductDetailsActivity::class.java)
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return productsModel.size
    }
}