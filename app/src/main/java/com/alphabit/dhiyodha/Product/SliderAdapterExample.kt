package com.alphabit.dhiyodha.Product

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.alphabit.dhiyodha.R
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapterExample(
    private var mSliderItems: MutableList<String>,
    val context: Context,
) :
    SliderViewAdapter<SliderAdapterExample.SliderAdapterVH>() {
    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.image_slider_layout_item, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {

        val model = mSliderItems[position]

        Glide.with(viewHolder.itemView)
            .load(model)
            .error(R.drawable.ic_category_girl).placeholder(R.drawable.ic_category_girl)
            .into(viewHolder.imageViewBackground)
    }

    override fun getCount(): Int {
        return mSliderItems.size
    }

    inner class SliderAdapterVH(itemView: View) : ViewHolder(itemView) {
        var imageViewBackground: ImageView

        init {
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider)
        }
    }
}