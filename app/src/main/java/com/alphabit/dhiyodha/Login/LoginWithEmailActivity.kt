package com.alphabit.dhiyodha.Login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alphabit.dhiyodha.Utils.AppUtils
import com.alphabit.dhiyodha.databinding.ActivityLoginWithEmailBinding

class LoginWithEmailActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginWithEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginWithEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpClickListener()
    }

    private fun setUpClickListener() {
        binding.apply {
            btnContinue.setOnClickListener {
                val i = Intent(this@LoginWithEmailActivity, EnterOTPActivity::class.java)
                startActivity(i)
            }
        }
    }

    private fun checkValidation(): Boolean {
        if (binding.edtEmail.text.isEmpty()) {
            binding.edtEmail.requestFocus()
            AppUtils.showAlertDialog(this@LoginWithEmailActivity, "Please Enter Email", "Ok")
            return false
        } else if (!AppUtils.isValidEmail(binding.edtEmail)) {
            binding.edtEmail.requestFocus()
            AppUtils.showAlertDialog(this@LoginWithEmailActivity, "Please Enter Valid Email", "Ok")
            return false
        }
        return true
    }
}