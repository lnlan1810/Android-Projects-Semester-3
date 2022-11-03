package com.example.hw5.second.recycler

import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.hw5.R
import com.example.hw5.model.DataItem
import com.example.hw5.model.Repository.TYPE_SINGER
import com.example.hw5.model.Repository.TYPE_SONG
import com.example.hw5.third.SongHolder

//не работает
class Adapter(
    private val glide: RequestManager,
    private val onItemClick: (Int) -> Unit,
    private val action: (item: DataItem.Singer) -> Unit
) :  androidx.recyclerview.widget.ListAdapter<DataItem, RecyclerView.ViewHolder>(
    object :  DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(
            oldItem: DataItem,
            newItem: DataItem
        ): Boolean {
            if (oldItem is DataItem.Singer && newItem is DataItem.Singer)
                return (oldItem.title == newItem.title && oldItem.desc == newItem.desc)
            if (oldItem is DataItem.Song && newItem is DataItem.Song)
                return oldItem.title == newItem.title
            return false
        }

        override fun areContentsTheSame(
            oldItem: DataItem,
            newItem: DataItem
        ): Boolean = oldItem == newItem
    }
) {

    private val singerList = arrayListOf<Any>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(viewType){
            R.layout.item_singer -> SingerHolder.create(parent, glide, action)
            R.layout.item_cv_song -> SongHolder.create(parent, glide)
            else -> throw IllegalAccessException("Invalid ViewType")
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is SingerHolder -> holder.bind(singerList[position] as DataItem.Singer)
            is SongHolder -> holder.bind(singerList[position] as DataItem.Song)
        }
    }

    override fun getItemCount(): Int = singerList.size

    override fun getItemViewType(position: Int): Int {
        return when (singerList[position]) {
            is DataItem.Singer -> TYPE_SINGER
            is DataItem.Song -> TYPE_SONG
            else -> throw IllegalArgumentException("Invalid Item")
        }
    }

}