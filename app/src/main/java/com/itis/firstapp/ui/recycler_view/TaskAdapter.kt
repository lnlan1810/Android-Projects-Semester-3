package com.itis.firstapp.ui.recycler_view

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.itis.firstapp.model.entities.Task

class TaskAdapter(
    private var onItemChosenAction: (Int) -> Unit,
    private var onItemDeleteAction: (Int) -> Unit
    ) : ListAdapter<Task, TaskHolder>(TaskDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : TaskHolder =
        TaskHolder.create(parent, onItemChosenAction, onItemDeleteAction)

    override fun onBindViewHolder(holder: TaskHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: MutableList<Task>?) {
        super.submitList(
            if (list == null) null
            else ArrayList(list))
    }
}

class TaskDiffUtilCallback : DiffUtil.ItemCallback<Task>() {

    override fun areItemsTheSame(oldItem: Task, newItem: Task) : Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Task, newItem: Task) : Boolean = areItemsTheSame(oldItem, newItem)

}
