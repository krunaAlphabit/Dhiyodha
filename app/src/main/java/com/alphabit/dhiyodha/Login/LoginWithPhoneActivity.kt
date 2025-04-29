package com.alphabit.dhiyodha.Login

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alphabit.dhiyodha.R
import com.alphabit.dhiyodha.databinding.ActivityLoginWithEmailBinding
import com.alphabit.dhiyodha.databinding.ActivityLoginWithPhoneBinding

class LoginWithPhoneActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginWithPhoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginWithPhoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpClickListener()
    }

    private fun setUpClickListener() {
        binding.apply {
            btnContinue.setOnClickListener {
                val i = Intent(this@LoginWithPhoneActivity, EnterOTPActivity::class.java)
                startActivity(i)
            }
        }
    }
}