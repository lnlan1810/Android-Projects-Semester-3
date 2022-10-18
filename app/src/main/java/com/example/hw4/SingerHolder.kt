package com.example.hw4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.hw4.databinding.ItemMemberBinding

class SingerHolder(private val binding: ItemMemberBinding,
                   private val glide: RequestManager,
                   private val action: (Int)-> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var windowItem: Singer? = null

    //one clickListener for each item
    init{
        itemView.setOnClickListener{
            windowItem?.id?.also(action)
        }
    }

    fun bind(item: Singer) {
        windowItem = item

        with(binding) {
            tvTitle.text = item.title

            glide.load(item.url).into(ivImage)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            glide: RequestManager,
            action: (Int)-> Unit
        ) = SingerHolder(
            ItemMemberBinding.inflate(
                LayoutInflater
                    .from(parent.context),
                parent,
                false
            ),
            glide,
            action
        )
    }
}
