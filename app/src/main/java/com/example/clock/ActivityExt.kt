package com.example.clock

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment

fun AppCompatActivity.findController(id: Int) =
    (supportFragmentManager.findFragmentById(id) as NavHostFragment)
        .navController
