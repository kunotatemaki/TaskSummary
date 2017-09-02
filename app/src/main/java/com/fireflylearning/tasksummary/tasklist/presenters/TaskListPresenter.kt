package com.fireflylearning.tasksummary.tasklist.presenters

import com.fireflylearning.tasksummary.model.Task
import com.fireflylearning.tasksummary.tasklist.views.TaskView

/**
 * Created by Roll on 31/8/17.
 */
interface TaskListPresenter {
    fun loadTasks()

    fun taskClicked(superHeroView: TaskView, superHero: Task)

}