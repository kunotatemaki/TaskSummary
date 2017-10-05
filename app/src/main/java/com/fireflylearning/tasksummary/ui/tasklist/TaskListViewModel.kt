package com.fireflylearning.tasksummary.ui.tasklist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.fireflylearning.tasksummary.model.CustomLiveData
import com.fireflylearning.tasksummary.model.TaskListLiveData
import com.fireflylearning.tasksummary.persistence.entities.Task
import com.fireflylearning.tasksummary.repository.TaskRepository
import com.fireflylearning.tasksummary.switchMap
import com.fireflylearning.tasksummary.utils.AbsentLiveData
import com.fireflylearning.tasksummary.utils.logger.AndroidLoggerHelperImpl
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.vo.Resource
import javax.inject.Inject

/**
 * Created by Roll on 31/8/17.
 * View model for the tasklistactivity
 */
class TaskListViewModel @Inject constructor(private val taskRepository: TaskRepository): ViewModel() {
    var showingEmpty: Boolean = false
    var host: String = ""
    var token: String = ""
    val tasks: CustomLiveData<MutableList<Task>> = TaskListLiveData()
    val logger: LoggerHelper = AndroidLoggerHelperImpl()

    private val query = MutableLiveData<Long>()

    private val listOfTasks: LiveData<Resource<PagedList<Task>>>

    init {
        query.value = 0L
        listOfTasks = query.switchMap  { date ->
            if (date == null || date == 0L) {
                AbsentLiveData.create()
            } else {
                taskRepository.loadTasks(token = token, host = host)
            }
        }
    }

    fun setQuery(date: Long){
        if(query.value == date)
            return
        query.value = date
    }

    fun getResults() : LiveData<Resource<PagedList<Task>>>{
        return listOfTasks
    }


}