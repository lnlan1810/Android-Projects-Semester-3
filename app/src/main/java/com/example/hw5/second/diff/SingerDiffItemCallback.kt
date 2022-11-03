package com.example.hw5.second.diff

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.example.hw5.model.DataItem

class SingerDiffItemCallback : DiffUtil.ItemCallback<DataItem.Singer>() {
    override fun areItemsTheSame(
        oldItem: DataItem.Singer,
        newItem: DataItem.Singer
    ): Boolean = oldItem.title == newItem.title

    override fun areContentsTheSame(
        oldItem: DataItem.Singer,
        newItem: DataItem.Singer
    ): Boolean = oldItem == newItem

    override fun getChangePayload(oldItem: DataItem.Singer, newItem: DataItem.Singer): Any? {
        val bundle = Bundle()
        if (oldItem.title != newItem.title) {
            bundle.putString("TITLE", newItem.title)
        }
        if (oldItem.desc != newItem.desc) {
            bundle.putString("DESC", newItem.desc)
        }
        if (oldItem.url != newItem.url) {
            bundle.putString("URL", newItem.url)
        }
        if (bundle.isEmpty) return null
        return bundle
    }
}