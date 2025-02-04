package com.alphabit.dhiyodha.BabyToys

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alphabit.dhiyodha.R
import com.alphabit.dhiyodha.databinding.ActivityBabyToysBinding

class BabyToysActivity : AppCompatActivity() {

    lateinit var binding: ActivityBabyToysBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBabyToysBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolBar()
    }

    private fun setUpToolBar() {
        binding.toolbar.tvTitle.text = "Baby’s & Toys"
        binding.toolbar.ivBack.setOnClickListener {
            finish()
        }
    }
}