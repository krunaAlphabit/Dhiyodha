package com.alphabit.dhiyodha.WomanFashion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alphabit.dhiyodha.databinding.ActivityWomanFashionBinding

class WomanFashionActivity : AppCompatActivity() {

    lateinit var binding: ActivityWomanFashionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWomanFashionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolBar()
    }

    private fun setUpToolBar() {
        binding.toolbar.tvTitle.text = "Woman's Fashion"
        binding.toolbar.ivBack.setOnClickListener {
            finish()
        }
    }
}