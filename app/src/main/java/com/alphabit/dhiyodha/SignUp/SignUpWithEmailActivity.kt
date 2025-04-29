package com.alphabit.dhiyodha.SignUp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alphabit.dhiyodha.Dashboard.DashboardActivity
import com.alphabit.dhiyodha.Login.EnterOTPActivity
import com.alphabit.dhiyodha.R
import com.alphabit.dhiyodha.databinding.ActivitySignUpWithEmailBinding

class SignUpWithEmailActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpWithEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpWithEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.apply {
            btnSignUp.setOnClickListener {
                val i = Intent(this@SignUpWithEmailActivity, DashboardActivity::class.java)
                startActivity(i)
            }
        }
    }
}