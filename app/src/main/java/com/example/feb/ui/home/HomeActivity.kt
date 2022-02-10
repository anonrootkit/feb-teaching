package com.example.feb.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.feb.R
import com.example.feb.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}