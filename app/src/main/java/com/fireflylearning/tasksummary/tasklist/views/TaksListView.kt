package com.fireflylearning.tasksummary.tasklist.views

import com.fireflylearning.tasksummary.model.CustomLiveData
import com.fireflylearning.tasksummary.model.Task
import com.fireflylearning.tasksummary.tasklist.lifecycleobservers.TaskListLifecycleObserver

/**
 * Created by Roll on 31/8/17.
 */
interface TaksListView {

    fun addLifecycleObserver(observer: TaskListLifecycleObserver)

    fun getLiveSuperHeroes(): CustomLiveData<MutableList<Task>>

    fun setSuperHeroesInView(superHeroes: List<Task>)

    fun showEmptyList(message: String)

    fun hideEmptyList()

    fun showLoader()

    fun hideLoader()

    fun showSuperHeroDetails(superHeroView: TaskView, superhero: Task)
}