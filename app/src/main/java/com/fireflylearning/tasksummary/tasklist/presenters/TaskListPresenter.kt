package com.fireflylearning.tasksummary.tasklist.presenters

import com.fireflylearning.tasksummary.model.Task
import com.fireflylearning.tasksummary.tasklist.views.TaskView

/**
 * Created by Roll on 31/8/17.
 */
interface TaskListPresenter {
    fun loadSuperHeroes()

    fun superHeroClicked(superHeroView: TaskView, superHero: Task)

}