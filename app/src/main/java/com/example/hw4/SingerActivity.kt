package com.example.hw4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.hw4.databinding.ActivitySingerBinding

class SingerActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySingerBinding
    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingerBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        intent?.extras?.getInt("ID")?.let { id ->
            SingerReponsitory.singers[id]?.let {

                with(binding) {

                    Glide.with(this.root).load(SingerReponsitory.singers[id!!].url).into(ivPromo)
                    tvName.text = "NAME: ${SingerReponsitory.singers[id!!].title}"
                    tvDesc.text = "DESCRIPTION: ${SingerReponsitory.singers[id!!].form}"
                }

            }
        }
    }
}