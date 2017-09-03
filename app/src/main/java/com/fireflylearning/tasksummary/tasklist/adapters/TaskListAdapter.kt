package com.fireflylearning.tasksummary.tasklist.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.databinding.ActivityTaskListRowBinding
import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
import com.fireflylearning.tasksummary.model.Task
import com.fireflylearning.tasksummary.tasklist.presenters.TaskListPresenter
import com.fireflylearning.tasksummary.tasklist.viewholders.TaskListViewHolder
import com.fireflylearning.tasksummary.utils.TaskUtils
import com.fireflylearning.tasksummary.utils.resources.ResourcesManager
import com.fireflylearning.tasksummary.utils.ui.GlideBindingComponent
import javax.inject.Inject

/**
 * Created by Roll on 31/8/17.
 */
@CustomScopes.ActivityScope
class TaskListAdapter @Inject constructor(val presenter: TaskListPresenter):
        RecyclerView.Adapter<TaskListViewHolder>() {

    @Inject
    lateinit var taskUtils: TaskUtils

    @Inject
    lateinit var resources: ResourcesManager

    val tasks: MutableList<Task> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TaskListViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val binding = DataBindingUtil.inflate<ActivityTaskListRowBinding>(inflater, R.layout.activity_task_list_row, parent,
                false, GlideBindingComponent())


        return TaskListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskListViewHolder?, position: Int) {
        val task: Task = tasks[position]
        holder?.bind(task, presenter, taskUtils, resources)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

}