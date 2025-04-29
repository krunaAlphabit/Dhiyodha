package com.alphabit.dhiyodha.SignUp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alphabit.dhiyodha.Login.LoginWithEmailActivity
import com.alphabit.dhiyodha.R
import com.alphabit.dhiyodha.databinding.ActivitySignUpOptionsBinding

class SignUpOptionsActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpClickListener()
    }

    private fun setUpClickListener() {
        binding.apply {
            btnSignUpWithEmail.setOnClickListener {
                val i = Intent(this@SignUpOptionsActivity, SignUpWithEmailActivity::class.java)
                startActivity(i)
            }
        }
    }
}