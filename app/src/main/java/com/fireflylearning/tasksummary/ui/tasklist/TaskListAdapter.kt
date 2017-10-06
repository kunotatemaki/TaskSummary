package com.fireflylearning.tasksummary.ui.tasklist

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.recyclerview.extensions.DiffCallback
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.databinding.TaskListRowBinding
import com.fireflylearning.tasksummary.di.interfaces.CustomScopes
import com.fireflylearning.tasksummary.persistence.entities.Task
import com.fireflylearning.tasksummary.ui.tasklist.viewholders.TaskListViewHolder
import com.fireflylearning.tasksummary.utils.TaskUtils
import com.fireflylearning.tasksummary.utils.resources.ResourcesManager

/**
 * Created by Roll on 31/8/17.
 * paged list adapter to retrieve data in pages from db
 */
@CustomScopes.ActivityScope
class TaskListAdapter constructor(private val taskUtils: TaskUtils,
                                          private val resourcesManager: ResourcesManager,
                                          private val taskClickCallback: TaskClickCallback):
        PagedListAdapter<Task, TaskListViewHolder>(diffCallback) {

    //val tasks: MutableList<Task> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TaskListViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val binding = DataBindingUtil.inflate<TaskListRowBinding>(inflater, R.layout.task_list_row, parent,
                false)

        return TaskListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskListViewHolder?, position: Int) {
        val task: Task? = getItem(position)
        holder?.bind(task, taskUtils, resourcesManager, taskClickCallback)
    }


    interface TaskClickCallback {
        fun onClick(task: Task?)
    }

    companion object {
        /**
         * This diff callback informs the PagedListAdapter how to compute list differences when new
         * PagedLists arrive.
         * <p>
         *
         *
         * @see android.support.v7.util.DiffUtil
         */
        private val diffCallback = object : DiffCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean =
                    oldItem.id == newItem.id

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean =
                    oldItem == newItem
        }
    }

}