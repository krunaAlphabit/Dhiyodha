package com.alphabit.dhiyodha.Login

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alphabit.dhiyodha.Cart.CartActivity
import com.alphabit.dhiyodha.Dashboard.DashboardActivity
import com.alphabit.dhiyodha.R
import com.alphabit.dhiyodha.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpClickListener()
    }

    private fun setUpClickListener() {
        binding.apply {
            tvContinue.setOnClickListener {
                val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}