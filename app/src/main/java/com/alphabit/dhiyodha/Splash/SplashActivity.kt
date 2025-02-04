package com.alphabit.dhiyodha.Splash

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alphabit.dhiyodha.App.SessionManager
import com.alphabit.dhiyodha.R
import com.alphabit.dhiyodha.Utils.AppUtils
import com.alphabit.dhiyodha.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding

    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppUtils.setStatusBar(window, this)
        sessionManager = SessionManager(this)

        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.side_slide)
        binding.ivAppLogo.startAnimation(slideAnimation)

        startCountDown()
    }

    private fun startCountDown() {
        object : Thread() {
            override fun run() {
                try {
                    sleep(2000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    sessionManager.checkLogin()
                }
            }
        }.start()
    }
}