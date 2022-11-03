package com.example.hw5.second.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw5.databinding.ItemSingerBinding
import com.bumptech.glide.RequestManager
import com.example.hw5.model.DataItem

class SingerHolder (
    private val binding: ItemSingerBinding,
    private val glide: RequestManager,
    private val clickListener: (item: DataItem.Singer) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var windowItem: DataItem.Singer? = null

    init{
        windowItem?.let { it1 -> clickListener.invoke(it1)}
    }

    fun bind(item: DataItem.Singer) {
        windowItem = item

        with(binding) {
            tvTitle.text = item.title
            tvDesc.text = item.desc

            glide.load(item.url).into(ivImage)
            btnDelete.setOnClickListener {
                clickListener.invoke(item)
            }
        }
    }


    fun updateFields(bundle: Bundle) {
        bundle.run {
            getString("TITLE")?.also {
                updateTitle(it)
            }
            getString("DESC")?.also {
                updateDesc(it)
            }
        }
    }

    fun updateTitle(title: String) {
        binding.tvTitle.text = title
    }

    fun updateDesc(desc: String) {
        binding.tvDesc.text = desc
    }


    companion object {
        fun create(
            parent: ViewGroup,
            glide: RequestManager,
            clickListener: (item: DataItem.Singer) -> Unit
        ) = SingerHolder(
            ItemSingerBinding.inflate(
                LayoutInflater
                    .from(parent.context),
                parent,
                false
            ),
            glide,
            clickListener
        )
    }
}
