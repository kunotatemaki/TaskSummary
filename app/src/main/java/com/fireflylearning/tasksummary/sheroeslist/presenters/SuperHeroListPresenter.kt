package com.fireflylearning.tasksummary.sheroeslist.presenters

import com.fireflylearning.tasksummary.model.SuperHero
import com.fireflylearning.tasksummary.sheroeslist.views.SuperHeroView

/**
 * Created by Roll on 31/8/17.
 */
interface SuperHeroListPresenter {
    fun loadSuperHeroes()

    fun superHeroClicked(superHeroView: SuperHeroView, superHero: SuperHero)

}