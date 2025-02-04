package com.alphabit.dhiyodha.SportsOutDoor

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alphabit.dhiyodha.R
import com.alphabit.dhiyodha.databinding.ActivitySportsOutdoorBinding

class SportsOutdoorActivity : AppCompatActivity() {

    lateinit var binding: ActivitySportsOutdoorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySportsOutdoorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolBar()
    }

    private fun setUpToolBar() {
        binding.toolbar.tvTitle.text = "Sports & Outdoor"
        binding.toolbar.ivBack.setOnClickListener {
            finish()
        }
    }
}