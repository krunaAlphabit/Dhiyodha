package com.alphabit.dhiyodha.Login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alphabit.dhiyodha.databinding.ActivityLoginOptionsBinding

class LoginOptionsActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpClickListener()
    }

    private fun setUpClickListener() {
        binding.apply {
            imgSignUpWithPhone.setOnClickListener {}

            btnSignUpWithEmail.setOnClickListener {
                val i = Intent(this@LoginOptionsActivity, LoginWithEmailActivity::class.java)
                startActivity(i)
            }

            imgSignUpWithPhone.setOnClickListener {
                val i = Intent(this@LoginOptionsActivity, LoginWithPhoneActivity::class.java)
                startActivity(i)
            }
        }
    }
}