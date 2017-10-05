package com.fireflylearning.tasksummary.tasklist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.fireflylearning.tasksummary.model.CustomLiveData
import com.fireflylearning.tasksummary.model.TaskListLiveData
import com.fireflylearning.tasksummary.persistence.entities.Task
import com.fireflylearning.tasksummary.repository.TaskRepository
import com.fireflylearning.tasksummary.repository.TaskRepository_Factory
import com.fireflylearning.tasksummary.switchMap
import com.fireflylearning.tasksummary.utils.AbsentLiveData
import com.fireflylearning.tasksummary.utils.AbsentLiveData.Companion.create
import com.fireflylearning.tasksummary.utils.logger.AndroidLoggerHelperImpl
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.vo.Resource
import javax.inject.Inject

/**
 * Created by Roll on 31/8/17.
 */
class TaskListViewModel @Inject constructor(val taskRepository: TaskRepository): ViewModel() {
    var showingEmpty: Boolean = false
    var host: String = ""
    var token: String = ""
    val tasks: CustomLiveData<MutableList<Task>> = TaskListLiveData()
    val logger: LoggerHelper = AndroidLoggerHelperImpl()

    private val query = MutableLiveData<String>()

    private val results: LiveData<Resource<List<Task>>>

    init {
        results = query.switchMap  {search ->
            if (search == null || search.trim { it <= ' ' }.isEmpty()) {
                AbsentLiveData.create()
            } else {
                taskRepository.loadRepos(token)
            }
        }
    }

    fun setQuery(text: String){
        query.value = text
    }

    fun getResults() : LiveData<Resource<List<Task>>>{
        return results
    }
}