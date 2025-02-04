package com.alphabit.dhiyodha.HealthBeauty

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alphabit.dhiyodha.R
import com.alphabit.dhiyodha.databinding.ActivityHealthBeautyBinding

class HealthBeautyActivity : AppCompatActivity() {

    lateinit var binding: ActivityHealthBeautyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHealthBeautyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolBar()
    }

    private fun setUpToolBar() {
        binding.toolbar.tvTitle.text = "Health & Beauty"
        binding.toolbar.ivBack.setOnClickListener {
            finish()
        }
    }
}