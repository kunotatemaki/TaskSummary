package com.fireflylearning.tasksummary.ui.tasklist.viewholders

import android.support.v7.widget.RecyclerView
import com.fireflylearning.tasksummary.databinding.ActivityTaskListRowBinding
import com.fireflylearning.tasksummary.persistence.entities.Task
import com.fireflylearning.tasksummary.ui.tasklist.TaskListAdapter
import com.fireflylearning.tasksummary.utils.TaskUtils
import com.fireflylearning.tasksummary.utils.resources.ResourcesManager

/**
 * Created by Roll on 31/8/17.
 * viewholder for task row
 */
class TaskListViewHolder(private val binding: ActivityTaskListRowBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(task: Task?, taskUtils: TaskUtils, resources: ResourcesManager, taskClickCallback: TaskListAdapter.TaskClickCallback) {
        binding.task = task
        binding.taskUtils = taskUtils
        binding.resources = resources
        binding.root.setOnClickListener { v ->
            taskClickCallback.onClick(task)
        }
        binding.executePendingBindings()
    }
}