package com.fireflylearning.tasksummary.sherodetails.views

import com.fireflylearning.tasksummary.sherodetails.lifecycleobservers.SuperHeroDetailLifecycleObserver

/**
 * Created by Roll on 31/8/17.
 */
interface SuperHeroDetailView {

    fun addLifecycleObserver(observer: SuperHeroDetailLifecycleObserver)

}