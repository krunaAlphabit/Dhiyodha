package com.alphabit.dhiyodha.SignUp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alphabit.dhiyodha.Dashboard.DashboardActivity
import com.alphabit.dhiyodha.R
import com.alphabit.dhiyodha.databinding.ActivitySignUpWithPhoneBinding

class SignUpWithPhoneActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpWithPhoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpWithPhoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpClickListener()
    }

    private fun setUpClickListener() {
        binding.apply {
            val i = Intent(this@SignUpWithPhoneActivity, DashboardActivity::class.java)
            startActivity(i)
        }
    }
}