package com.example.hw5.third

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.hw5.databinding.ItemCvSongBinding
import com.example.hw5.model.Song

class SongHolder (
    private val binding: ItemCvSongBinding,
    private val glide: RequestManager,
) : RecyclerView.ViewHolder(binding.root) {
    private var house: Song? = null


    fun bind(item: Song) {
        house = item
        with(binding) {
            house?.let {
                tvTitle.text = it.title
                tvDesc.text = it.desc
                vp2Images.adapter = ViewPagerAdapter(it.images, glide)
            }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            glide: RequestManager,
        ) = SongHolder(
            ItemCvSongBinding.inflate(
                LayoutInflater
                    .from(parent.context),
                parent,
                false
            ),
            glide
        )
    }
}
