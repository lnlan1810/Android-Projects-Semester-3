package com.example.hw4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.hw4.databinding.ActivityRecyclerBinding

class recyclerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerBinding
    private var singerAdapter: SingerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        singerAdapter = SingerAdapter(SingerReponsitory.singers, Glide.with(this)) {
            showSelectedTitle(it)
        }
        binding.rvSingers.run {
            adapter = singerAdapter
        }
    }
    private fun showSelectedTitle(id: Int) {
        startActivity(
            Intent(this,
            SingerActivity::class.java
        )
            .apply {
                putExtra("ID", id)
            })

    }
}
