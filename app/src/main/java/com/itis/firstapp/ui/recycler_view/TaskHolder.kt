package com.itis.firstapp.ui.recycler_view

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itis.firstapp.databinding.ItemTaskBinding
import com.itis.firstapp.model.entities.Task

class TaskHolder(
    private val binding: ItemTaskBinding,
    private val onItemChosenAction: (Int) -> Unit,
    private val onItemDeleteAction: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SimpleDateFormat")
    fun bind(item: Task) =
        with(binding) {
            tvTitle.text =
                if (item.title != "")
                    item.title
                else "No title of task"
            tvTime.text =
                if (item.date != null)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        SimpleDateFormat("dd.MM.yyyy").format(item.date)
                    } else {
                        ""
                    }
                else "No time"
            tvDescription.text =
                if (item.description != "")
                    item.description
                else "No description of task"
            tvLongitude.text =
                if (item.longitude != null)
                     "Longitude: ${item.longitude.toString()}"
                else "Longitude: -"
            tvLatitude.text =
                if (item.latitude != null)
                    "Latitude: ${item.latitude.toString()}"
                else "Latitude: -"

        itemView.setOnClickListener {
            onItemChosenAction.invoke(item.id)
        }

        deleteTaskBtn.setOnClickListener {
            onItemDeleteAction.invoke(item.id)
        }
    }

    companion object {
        fun create(parent: ViewGroup,
                   onItemChosenAction: (Int) -> Unit,
                   onItemDeleteAction: (Int) -> Unit) =
            TaskHolder (
                ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                onItemChosenAction, onItemDeleteAction)
    }
}
