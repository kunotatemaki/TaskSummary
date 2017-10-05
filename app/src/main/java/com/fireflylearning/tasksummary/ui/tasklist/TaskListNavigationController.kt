package com.fireflylearning.tasksummary.ui.tasklist

import android.support.v4.app.FragmentManager
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.ui.tasklist.views.TaskListActivity
import javax.inject.Inject

/**
 * Created by Roll on 5/10/17.
 * A utility class that handles navigation in {@link TaskListActivity}.
 */


class TaskListNavigationController @Inject
constructor(taskListActivity: TaskListActivity) {
    private val containerId: Int = R.id.container
    private val fragmentManager: FragmentManager = taskListActivity.supportFragmentManager

    fun navigateToListOfTasks(host: String, token: String) {
        val taskListFragment = TaskListFragment.newInstance(host, token)
        fragmentManager.beginTransaction()
                .replace(containerId, taskListFragment)
                .commitAllowingStateLoss()
    }

}
