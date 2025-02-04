package com.alphabit.dhiyodha.GroceriesPets

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alphabit.dhiyodha.R
import com.alphabit.dhiyodha.databinding.ActivityGroceriesPetsBinding

class GroceriesPetsActivity : AppCompatActivity() {

    lateinit var binding: ActivityGroceriesPetsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGroceriesPetsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolBar()
    }

    private fun setUpToolBar() {
        binding.toolbar.tvTitle.text = "Groceries & Pets"
        binding.toolbar.ivBack.setOnClickListener {
            finish()
        }
    }
}