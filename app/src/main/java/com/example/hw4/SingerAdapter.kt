package com.example.hw4

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager

class SingerAdapter(private val list: ArrayList<Singer>,
                    private val glide: RequestManager,
                    private val action: (Int)-> Unit
) : RecyclerView.Adapter<SingerHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int)
            : SingerHolder = SingerHolder.create(parent, glide, action)

    override fun onBindViewHolder(holder: SingerHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
