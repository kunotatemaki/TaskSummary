package com.fireflylearning.tasksummary.tasklist.viewholders

import android.support.v7.widget.RecyclerView
import com.fireflylearning.tasksummary.databinding.SuperheroItemBinding
import com.fireflylearning.tasksummary.model.Task
import com.fireflylearning.tasksummary.tasklist.presenters.TaskListPresenter

/**
 * Created by Roll on 31/8/17.
 */
class TaskListViewHolder(private val binding: SuperheroItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(task: Task, presenter: TaskListPresenter) {
        binding.task = task
        binding.presenter = presenter
        binding.executePendingBindings()
    }
}