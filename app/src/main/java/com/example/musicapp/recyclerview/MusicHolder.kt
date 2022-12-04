package com.example.musicapp.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.Music
import com.example.musicapp.databinding.ItemMusicBinding

class MusicHolder (
    private val binding: ItemMusicBinding,
    private val action: (Int)-> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var track: Music? = null

    init{
        itemView.setOnClickListener{
            track?.id?.also(action)
        }
    }


    fun bind(item: Music) {
        track = item

        with(binding) {
            tvTitle.text = item.title
            tvAuthor.text = item.author
            ivCover.setImageResource(item.cover)
        }
    }


    companion object {
        fun create(
            parent: ViewGroup,
            action: (Int) -> Unit
        ) = MusicHolder(
            ItemMusicBinding.inflate(
                LayoutInflater
                    .from(parent.context),
                parent,
                false
            ),
            action
        )
    }
}
