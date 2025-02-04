package com.alphabit.dhiyodha.Dashboard

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.mutableIntListOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.alphabit.dhiyodha.Cart.CartActivity
import com.alphabit.dhiyodha.R
import com.alphabit.dhiyodha.Wishlist.WishlistActivity
import com.alphabit.dhiyodha.databinding.ActivityDashboardBinding
import com.alphabit.dhiyodha.databinding.RowDashboardBannerBinding
import com.alphabit.dhiyodha.databinding.RowDashboardCouponBannerBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.imaginativeworld.whynotimagecarousel.utils.setImage
import org.json.JSONObject
import java.lang.reflect.Type

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var categoriesAdapter: DashboardCategoriesAdapter
    val categoriesModel = ArrayList<DashboardCategoriesModel>()
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpClickListener()
        setCategoriesAdapter()
        setUpBanner()
        setUpBannerForCoupon()
        setUpBannerForSecondCoupon()
    }

    private fun setUpClickListener() {
        binding.toolbar.ivCart.setOnClickListener {
            val intent = Intent(this@DashboardActivity, CartActivity::class.java)
            startActivity(intent)
        }

        binding.toolbar.ivWishlist.setOnClickListener {
            val intent = Intent(this@DashboardActivity, WishlistActivity::class.java)
            startActivity(intent)
        }

        binding.toolbar.ivDrawer.setOnClickListener {
            if (binding.mDrawerLayout.isDrawerOpen(binding.navDrawer)) {
                binding.mDrawerLayout.closeDrawer(binding.navDrawer)
            } else if (!binding.mDrawerLayout.isDrawerOpen(binding.navDrawer)) {
                binding.mDrawerLayout.openDrawer(binding.navDrawer)
            }
        }
    }

    private fun setUpBannerForCoupon() {
        val carousel: ImageCarousel = binding.imageCarouselViewCoupon
        carousel.registerLifecycle(lifecycle)

        binding.imageCarouselViewCoupon.carouselListener = object : CarouselListener {
            override fun onCreateViewHolder(
                layoutInflater: LayoutInflater, parent: ViewGroup
            ): ViewBinding {
                return RowDashboardCouponBannerBinding.inflate(
                    layoutInflater, parent, false
                )
            }

            override fun onBindViewHolder(
                binding: ViewBinding, item: CarouselItem, position: Int
            ) {
                val currentBinding = binding as RowDashboardCouponBannerBinding

                currentBinding.imageView.apply {
                    scaleType = ImageView.ScaleType.FIT_XY
                    setImage(item, R.drawable.ic_coupon)
                }
            }
        }

        for (i in 0 until 10) {
            carousel.addData(CarouselItem(i))
        }
    }

    private fun setUpBannerForSecondCoupon() {
        val carousel: ImageCarousel = binding.imageCarouselViewCouponSecond
        carousel.registerLifecycle(lifecycle)

        binding.imageCarouselViewCouponSecond.carouselListener = object : CarouselListener {
            override fun onCreateViewHolder(
                layoutInflater: LayoutInflater, parent: ViewGroup
            ): ViewBinding {
                return RowDashboardCouponBannerBinding.inflate(
                    layoutInflater, parent, false
                )
            }

            override fun onBindViewHolder(
                binding: ViewBinding, item: CarouselItem, position: Int
            ) {
                val currentBinding = binding as RowDashboardCouponBannerBinding

                currentBinding.imageView.apply {
                    scaleType = ImageView.ScaleType.FIT_XY
                    setImage(item, R.drawable.ic_coupon)
                }
            }
        }

        for (i in 0 until 10) {
            carousel.addData(CarouselItem(i))
        }
    }

    private fun setUpBanner() {
        val carousel: ImageCarousel = binding.imageCarouselView
        carousel.registerLifecycle(lifecycle)

        binding.imageCarouselView.carouselListener = object : CarouselListener {
            override fun onCreateViewHolder(
                layoutInflater: LayoutInflater, parent: ViewGroup
            ): ViewBinding {
                return RowDashboardBannerBinding.inflate(
                    layoutInflater, parent, false
                )
            }

            override fun onBindViewHolder(
                binding: ViewBinding, item: CarouselItem, position: Int
            ) {
                val currentBinding = binding as RowDashboardBannerBinding

                currentBinding.imageView.apply {
                    scaleType = ImageView.ScaleType.FIT_XY
                    setImage(item, R.drawable.ic_dashboard_banner)
                }
            }
        }

        for (i in 0 until 10) {
            carousel.addData(CarouselItem(i))
        }
    }

    private fun setCategoriesAdapter() {
        categoriesAdapter = DashboardCategoriesAdapter()
        layoutManager = LinearLayoutManager(this@DashboardActivity, RecyclerView.HORIZONTAL, false)
        binding.rcvCategories.adapter = categoriesAdapter
        binding.rcvCategories.layoutManager = layoutManager
    }
}