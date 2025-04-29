package com.alphabit.dhiyodha.Login

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alphabit.dhiyodha.Dashboard.DashboardActivity
import com.alphabit.dhiyodha.R
import com.alphabit.dhiyodha.databinding.ActivityEnterOtpactivityBinding

class EnterOTPActivity : AppCompatActivity() {

    lateinit var binding: ActivityEnterOtpactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEnterOtpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpClickListener()
    }

    private fun setUpClickListener() {
        binding.apply {
            btnContinue.setOnClickListener {
                val i = Intent(this@EnterOTPActivity, DashboardActivity::class.java)
                startActivity(i)
            }
        }
    }
}