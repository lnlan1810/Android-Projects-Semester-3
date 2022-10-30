package com.example.hw5.third

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.hw5.model.Song

class SongAdapter (
    private val list: List<Song>,
    private val glide: RequestManager,
) : RecyclerView.Adapter<SongHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SongHolder = SongHolder.create(parent, glide)

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}
