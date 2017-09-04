package com.fireflylearning.tasksummary.tasklist.viewholders

import android.support.v7.widget.RecyclerView
import com.fireflylearning.tasksummary.databinding.ActivityTaskListRowBinding
import com.fireflylearning.tasksummary.persistence.entities.Task
import com.fireflylearning.tasksummary.tasklist.presenters.TaskListPresenter
import com.fireflylearning.tasksummary.utils.TaskUtils
import com.fireflylearning.tasksummary.utils.resources.ResourcesManager

/**
 * Created by Roll on 31/8/17.
 */
class TaskListViewHolder(private val binding: ActivityTaskListRowBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(task: Task, presenter: TaskListPresenter, taskUtils: TaskUtils, resources: ResourcesManager) {
        binding.task = task
        binding.presenter = presenter
        binding.taskUtils = taskUtils
        binding.resources = resources
        binding.executePendingBindings()
    }
}