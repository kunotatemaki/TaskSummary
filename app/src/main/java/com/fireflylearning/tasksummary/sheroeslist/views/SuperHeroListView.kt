package com.fireflylearning.tasksummary.sheroeslist.views

import com.fireflylearning.tasksummary.model.CustomLiveData
import com.fireflylearning.tasksummary.model.SuperHero
import com.fireflylearning.tasksummary.sheroeslist.lifecycleobservers.SuperHeroListLifecycleObserver

/**
 * Created by Roll on 31/8/17.
 */
interface SuperHeroListView {

    fun addLifecycleObserver(observer: SuperHeroListLifecycleObserver)

    fun getLiveSuperHeroes(): CustomLiveData<MutableList<SuperHero>>

    fun setSuperHeroesInView(superHeroes: List<SuperHero>)

    fun showEmptyList(message: String)

    fun hideEmptyList()

    fun showLoader()

    fun hideLoader()

    fun showSuperHeroDetails(superHeroView: SuperHeroView, superhero: SuperHero)
}