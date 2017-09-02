package com.fireflylearning.tasksummary.tasklist.viewholders

import android.support.v7.widget.RecyclerView
import com.fireflylearning.tasksummary.databinding.SuperheroItemBinding
import com.fireflylearning.tasksummary.model.Task
import com.fireflylearning.tasksummary.tasklist.presenters.TaskListPresenter

/**
 * Created by Roll on 31/8/17.
 */
class TaskListViewHolder(val binding: SuperheroItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(superhero: Task, presenter: TaskListPresenter) {
        binding.superhero = superhero
        binding.presenter = presenter
        binding.executePendingBindings()
    }
}