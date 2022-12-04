package com.example.musicapp.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.Music

class MusicAdapter (
    private val list: ArrayList<Music>,
    private val action: (Int) -> Unit
): RecyclerView.Adapter<MusicHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int)
            : MusicHolder = MusicHolder.create(parent, action)

    override fun onBindViewHolder(holder: MusicHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}