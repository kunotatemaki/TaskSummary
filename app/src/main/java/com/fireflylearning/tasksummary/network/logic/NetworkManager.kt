package com.fireflylearning.tasksummary.network.logic

import com.fireflylearning.tasksummary.model.CustomLiveData
import com.fireflylearning.tasksummary.model.Task
import com.fireflylearning.tasksummary.utils.FireflyConstants

/**
 * Created by Roll on 31/8/17.
 */
interface NetworkManager {

    fun getListOfTasks(host: String, token: String, tasks: CustomLiveData<MutableList<Task>>)

    fun login(host: String, token: String, status: CustomLiveData<FireflyConstants.TokenError>)

}