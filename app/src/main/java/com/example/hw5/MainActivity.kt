package com.example.hw5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import com.example.hw5.databinding.ActivityMainBinding
import androidx.navigation.ui.setupWithNavController


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityMainBinding.inflate(layoutInflater).also {
           setContentView(it.root)
       }

        controller = findController(R.id.container)
        binding.bnvMain.setupWithNavController(controller)
    }
}