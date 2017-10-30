package com.fireflylearning.tasksummary.ui.tasklist

import android.support.v7.widget.RecyclerView
import com.fireflylearning.tasksummary.databinding.TaskListRowBinding
import com.fireflylearning.tasksummary.persistence.entities.Task
import com.fireflylearning.tasksummary.utils.TaskUtils
import com.rukiasoft.utilslibrary.resources.ResourcesManager

/**
 * Created by Roll on 31/8/17.
 * viewholder for task row
 */
class TaskListViewHolder(private val binding: TaskListRowBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(task: Task?, taskUtils: TaskUtils, resources: ResourcesManager, taskClickCallback: TaskListAdapter.TaskClickCallback) {
        binding.task = task
        binding.taskUtils = taskUtils
        binding.resources = resources
        binding.root.setOnClickListener {
            taskClickCallback.onClick(binding.task)
        }
        binding.executePendingBindings()
    }
}