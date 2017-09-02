package com.fireflylearning.tasksummary.network.logic

import com.fireflylearning.tasksummary.model.CustomLiveData
import com.fireflylearning.tasksummary.model.Task

/**
 * Created by Roll on 31/8/17.
 */
interface NetworkManager {

    fun getSuperHeroes(superHeroes: CustomLiveData<MutableList<Task>>)

}