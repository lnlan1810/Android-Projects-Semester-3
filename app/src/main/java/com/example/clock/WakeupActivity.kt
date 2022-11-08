package com.example.clock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.clock.databinding.ActivityWakeupBinding

class WakeupActivity: AppCompatActivity() {
    private lateinit var binding: ActivityWakeupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWakeupBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
    }
}
