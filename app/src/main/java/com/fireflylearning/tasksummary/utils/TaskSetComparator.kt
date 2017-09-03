package com.fireflylearning.tasksummary.utils

import com.fireflylearning.tasksummary.objects.Task
import java.util.Comparator

/**
 * Created by Roll on 3/9/17.
 */

class TaskSetComparator : Comparator<Task> {
    override fun compare(left: Task, right: Task): Int {
        return left.set.compareTo(right.set)
    }
}