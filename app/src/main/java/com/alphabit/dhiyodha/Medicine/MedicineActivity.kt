package com.alphabit.dhiyodha.Medicine

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alphabit.dhiyodha.R
import com.alphabit.dhiyodha.databinding.ActivityMedicineBinding

class MedicineActivity : AppCompatActivity() {

    lateinit var binding: ActivityMedicineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMedicineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolBar()
    }

    private fun setUpToolBar() {
        binding.toolbar.tvTitle.text = "Medicine"
        binding.toolbar.ivBack.setOnClickListener {
            finish()
        }
    }
}