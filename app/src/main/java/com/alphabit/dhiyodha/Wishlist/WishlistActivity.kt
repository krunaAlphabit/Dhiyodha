package com.alphabit.dhiyodha.Wishlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.alphabit.dhiyodha.Utils.GridSpacingItemDecoration
import com.alphabit.dhiyodha.databinding.ActivityWishlistBinding

class WishlistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWishlistBinding
    private lateinit var wishlistItemAdapter: WishlistItemAdapter
    private var mGridLayoutManager: GridLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWishlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolBar()
        setUpWishListRv()
    }

    private fun setUpWishListRv() {
        wishlistItemAdapter = WishlistItemAdapter(this, mutableListOf())
        mGridLayoutManager = GridLayoutManager(this@WishlistActivity, 2)
        binding.rcvWishlist.addItemDecoration(GridSpacingItemDecoration(2, 0, true, 0))
        binding.rcvWishlist.layoutManager = mGridLayoutManager
        binding.rcvWishlist.adapter = wishlistItemAdapter
    }

    private fun setUpToolBar() {
        binding.toolbar.tvTitle.text = "My Wishlist"
        binding.toolbar.ivBack.setOnClickListener {
            finish()
        }
    }
}