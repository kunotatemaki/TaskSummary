package com.fireflylearning.tasksummary.tasklist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
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
    private val sessionStatus: MutableLiveData<Boolean> = MutableLiveData()
    val tasks: CustomLiveData<MutableList<Task>> = TaskListLiveData()
    val logger: LoggerHelper = AndroidLoggerHelperImpl()

    private val query = MutableLiveData<Long>()

    private val listOfTasks: LiveData<Resource<List<Task>>>

    init {
        query.value = 0L
        sessionStatus.value = true
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

    fun getResults() : LiveData<Resource<List<Task>>>{
        return listOfTasks
    }

    fun getSessionStatus(): LiveData<Boolean>{
        return sessionStatus
    }
}