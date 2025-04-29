package com.alphabit.dhiyodha.Product

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.alphabit.dhiyodha.App.Constants
import com.alphabit.dhiyodha.Retrofit.ApiServiceProvider
import com.alphabit.dhiyodha.Utils.GridSpacingItemDecoration
import com.alphabit.dhiyodha.Wishlist.WishlistItemAdapter
import com.alphabit.dhiyodha.databinding.ActivityProductListingBinding
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.lang.reflect.Type

class ProductListingActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductListingBinding
    private lateinit var wishlistItemAdapter: WishlistItemAdapter
    private var mGridLayoutManager: GridLayoutManager? = null
    private lateinit var productsModel: MutableList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductListingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolBar()
        getProducts()
    }

    private fun getProducts() {
        val browsePath = "6648217031/6648218031/7459781031/1968024031"
        val categoryId = "1968120031"

        ApiServiceProvider.getInstance(this@ProductListingActivity)
            .getCategoryWiseProduct(
                Constants.Urlpath.FETCH_PRODUCT_BY_CATEGORY,
                browsePath,
                categoryId,
                true
            ) { response ->
                try {
                    val responseObj = JSONObject(response.body().toString())
                    val status = responseObj.getString("status")
                    if (status == "OK") {
                        val responseObj = JSONObject(response.body().toString())
                        if (response.isSuccessful && response.code() == 200) {
                            val dataArray = responseObj.getJSONArray("data")
                            productsModel = Gson().fromJson<List<Product>>(
                                dataArray.toString(),
                                object : TypeToken<List<Product>>() {}.type
                            ) as MutableList<Product>
                            setUpWishListRv()
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
    }

    private fun setUpWishListRv() {
        wishlistItemAdapter = WishlistItemAdapter(this@ProductListingActivity, productsModel)
        mGridLayoutManager = GridLayoutManager(this@ProductListingActivity, 2)
        binding.rcvProducts.addItemDecoration(GridSpacingItemDecoration(2, 5, true, 0))
        binding.rcvProducts.layoutManager = mGridLayoutManager
        binding.rcvProducts.adapter = wishlistItemAdapter
    }

    private fun setUpToolBar() {
        binding.toolbar.ivBack.setOnClickListener {
            finish()
        }
    }
}