package com.example.hw5.second.recycler

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.example.hw5.model.DataItem
import com.example.hw5.model.Repository
import com.example.hw5.second.diff.SingerDiffItemCallback

class SingerListAdapter (
    private val glide: RequestManager,
    private val action: (item: DataItem.Singer) -> Unit
) : ListAdapter<DataItem.Singer, SingerHolder>(SingerDiffItemCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SingerHolder = SingerHolder.create(parent, glide, action)

    override fun onBindViewHolder(
        holder: SingerHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: SingerHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            payloads.last().takeIf { it is Bundle }?.let {
                holder.updateFields(it as Bundle)
            }
        }
    }

    override fun submitList(list: MutableList<DataItem.Singer>?) {
        super.submitList(if (list == null) null else ArrayList(list))
    }

    fun removeItem(item: DataItem.Singer) {
        Repository.singers.remove(item)
        submitList(Repository.singers)
    }
}
