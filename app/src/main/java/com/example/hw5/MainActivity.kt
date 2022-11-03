package com.example.hw5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import com.example.hw5.databinding.ActivityMainBinding
import androidx.navigation.ui.setupWithNavController
import com.example.hw5.second.SecondFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        if (savedInstanceState != null) {
            return
        }

        supportFragmentManager.beginTransaction()
            .add(
                R.id.fragment_container,
                SecondFragment()
            )
            .commit()
    }
}