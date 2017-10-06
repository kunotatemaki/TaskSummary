package com.fireflylearning.tasksummary.ui.tasklist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.persistence.entities.Task
import com.fireflylearning.tasksummary.repository.TaskRepository
import com.fireflylearning.tasksummary.switchMap
import com.fireflylearning.tasksummary.utils.AbsentLiveData
import com.fireflylearning.tasksummary.utils.FireflyConstants
import com.fireflylearning.tasksummary.utils.resources.ResourcesManager
import com.fireflylearning.tasksummary.vo.Resource
import javax.inject.Inject

/**
 * Created by Roll on 31/8/17.
 * View model for the tasklistactivity
 */
class TaskListViewModel @Inject constructor(private val taskRepository: TaskRepository,
                                            private val resourcesManager: ResourcesManager): ViewModel() {
    private var taskAction: MutableLiveData<TaskAction<String>> = MutableLiveData()
    private var host: String = ""
    private var token: String = ""

    private val query = MutableLiveData<Long>()

    private var listOfTasks: LiveData<Resource<PagedList<Task>>>

    init {
        taskAction.value = TaskAction.reset()
        query.value = System.currentTimeMillis()
        listOfTasks = query.switchMap  { date ->
            if (date == null || date == 0L) {
                AbsentLiveData.create()
            } else {
                taskRepository.loadTasks(token = token, host = host)
            }
        }
    }

    fun setToken(token: String){
        this.token = token
    }

    fun setHost(host: String){
        this.host = host
    }


    fun refresh(){
        query.value = System.currentTimeMillis()
    }



    fun getResults() : LiveData<Resource<PagedList<Task>>>{
        return listOfTasks
    }

    fun getTaskAction(): LiveData<TaskAction<String>> = taskAction

    fun resetTaskAction(){
        taskAction.value = TaskAction.reset()
    }
    fun taskClicked(task: Task) {
        if(task.descriptionPageUrl == null){
            taskAction.value = TaskAction.noInfo(resourcesManager.getString(R.string.no_description))
        }else{
            var url = resourcesManager.getString(R.string.details_url)
            url = url.replace("<host>", host)
            url = url.replace("<deviceId>", FireflyConstants.DEVICE_ID)
            url = url.replace("<secret>", token)
            url = url.replace("<url from api>", task.descriptionPageUrl)
            taskAction.value = TaskAction.withInfo(url)
        }

    }


}