package com.alphabit.dhiyodha.Product

import android.R.attr.text
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alphabit.dhiyodha.App.Constants
import com.alphabit.dhiyodha.Retrofit.ApiServiceProvider
import com.alphabit.dhiyodha.databinding.ActivityProductDetailsBinding
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations

class ProductDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductDetailsBinding

    private lateinit var productsModel: MutableList<Product>
    private var adapter: SliderAdapterExample? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolBar()
        getProducts()
    }

    private fun getProducts() {

        val browsePath = "6648217031/6648218031/7459781031/1968024031"
        val categoryId = "1968120031"

        ApiServiceProvider.getInstance(this@ProductDetailsActivity)
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

                            val imagesArray: MutableList<String> = productsModel[0].imagesUrl

                            setProductData(imagesArray)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
    }

    private fun setProductData(imagesArray: MutableList<String>) {
        if (productsModel[0].imagesUrl.size > 1) {
            adapter = SliderAdapterExample(imagesArray, this@ProductDetailsActivity)
            binding.icProductImages.setSliderAdapter(adapter!!)
            binding.icProductImages.setIndicatorAnimation(IndicatorAnimationType.SLIDE)
            binding.icProductImages.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            binding.icProductImages.indicatorSelectedColor = Color.GRAY
            binding.icProductImages.indicatorUnselectedColor = Color.WHITE
        } else {
            adapter = SliderAdapterExample(imagesArray, this@ProductDetailsActivity)
            binding.icProductImages.setSliderAdapter(adapter!!)
            binding.icProductImages.setIndicatorAnimation(IndicatorAnimationType.SLIDE)
            binding.icProductImages.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            binding.icProductImages.indicatorSelectedColor = Color.GRAY
            binding.icProductImages.indicatorUnselectedColor = Color.WHITE
            binding.icProductImages.setInfiniteAdapterEnabled(false)
        }

        binding.tvItemName.text = productsModel[0].productName
        binding.tvItemSubTitle.text = productsModel[0].description
        binding.tvProductOldPrice.text = "MRP " + productsModel[0].mrp
        binding.tvProductNewPrice.text = " " + productsModel[0].listingPrice
    }

    private fun setUpToolBar() {
        binding.toolbar.ivBack.setOnClickListener {
            finish()
        }

        binding.imgWishlist.setOnClickListener {
            addItemIntoWishList()
        }

        binding.btnBuyNow.setOnClickListener {
            val uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", "test@upi")
                .appendQueryParameter("pn", "Test User")
                .appendQueryParameter("am", "100")
                .appendQueryParameter("cu", "INR")
                .build()

            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = uri
            }

            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "No UPI app found", Toast.LENGTH_SHORT).show()
            }
        }

        binding.imgShare.setOnClickListener {
            showSharePopup(this, "Hey! Check out this cool T-shirt on Dhiyodha")
        }
    }

    private fun addItemIntoWishList() {
        val requestData = JsonObject()

        ApiServiceProvider.getInstance(this@ProductDetailsActivity)
            .sendPostData(
                Constants.Urlpath.ADD_ITEM_INTO_WISHLIST,
                requestData,
                true
            ) { response ->
                try {
                    val responseObj = JSONObject(response.body().toString())

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
    }

    fun showSharePopup(context: Context, textToShare: String) {
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, textToShare)
        }

        val shareIntent = Intent.createChooser(sendIntent, "Share via")
        context.startActivity(shareIntent)
    }

}
