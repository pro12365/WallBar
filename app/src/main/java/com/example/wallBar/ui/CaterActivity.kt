package com.example.wallBar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.wallBar.R
import com.example.wallBar.databinding.ActivityCaterBinding

class CaterActivity : AppCompatActivity() {
    lateinit var binding: ActivityCaterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
binding= ActivityCaterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}