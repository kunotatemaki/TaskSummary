package com.fireflylearning.tasksummary.tasklist.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.databinding.SuperheroItemBinding
import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
import com.fireflylearning.tasksummary.model.Task
import com.fireflylearning.tasksummary.tasklist.presenters.TaskListPresenter
import com.fireflylearning.tasksummary.tasklist.viewholders.TaskListViewHolder
import com.fireflylearning.tasksummary.utils.ui.GlideBindingComponent
import javax.inject.Inject

/**
 * Created by Roll on 31/8/17.
 */
@CustomScopes.ActivityScope
class TaskListAdapter @Inject constructor(val presenter: TaskListPresenter):
        RecyclerView.Adapter<TaskListViewHolder>() {

    val superHeroes: MutableList<Task> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TaskListViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val binding = DataBindingUtil.inflate<SuperheroItemBinding>(inflater, R.layout.superhero_item, parent,
                false, GlideBindingComponent())


        return TaskListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskListViewHolder?, position: Int) {
        val superHero: Task = superHeroes[position]
        holder?.bind(superHero, presenter)
    }

    override fun getItemCount(): Int {
        return superHeroes.size
    }

}