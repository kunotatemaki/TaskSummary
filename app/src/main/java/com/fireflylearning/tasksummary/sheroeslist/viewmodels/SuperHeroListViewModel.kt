package com.fireflylearning.tasksummary.sheroeslist.viewmodels

import android.arch.lifecycle.ViewModel
import com.fireflylearning.tasksummary.model.CustomLiveData
import com.fireflylearning.tasksummary.model.SuperHeroesLiveData
import com.fireflylearning.tasksummary.model.SuperHero

/**
 * Created by Roll on 31/8/17.
 */
class SuperHeroListViewModel: ViewModel() {
    var showingEmpty: Boolean = false
    val superheroes: CustomLiveData<MutableList<SuperHero>> = SuperHeroesLiveData()
}