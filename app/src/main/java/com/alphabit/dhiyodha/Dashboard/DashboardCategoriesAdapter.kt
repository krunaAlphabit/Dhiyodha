package com.alphabit.dhiyodha.Dashboard

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.alphabit.dhiyodha.Login.LoginWithEmailActivity
import com.alphabit.dhiyodha.Product.ProductDetailsActivity
import com.alphabit.dhiyodha.Product.ProductListingActivity
import com.alphabit.dhiyodha.R
import com.alphabit.dhiyodha.databinding.ActivityProductListingBinding
import kotlin.jvm.java

class DashboardCategoriesAdapter(
    private val context: Context?,
    private val categories: List<DashboardCategoriesModel>,
) :
    RecyclerView.Adapter<DashboardCategoriesAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_fragment, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.textView.text = category.name
        holder.imageView.setImageResource(category.imageRes)

        holder.itemView.setOnClickListener {
            val i = Intent(context, ProductListingActivity::class.java)
            context!!.startActivity(i)
        }
    }

    override fun getItemCount(): Int = categories.size
}