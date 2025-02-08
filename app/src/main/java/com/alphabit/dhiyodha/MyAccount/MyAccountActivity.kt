package com.alphabit.dhiyodha.MyAccount

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alphabit.dhiyodha.R
import com.alphabit.dhiyodha.databinding.ActivityMyAccountBinding

class MyAccountActivity : AppCompatActivity() {

    lateinit var binding: ActivityMyAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolBar()
    }

    private fun setUpToolBar() {
        binding.toolbar.tvTitle.text = "My Account"
        binding.toolbar.ivBack.setOnClickListener {
            finish()
        }
    }

}