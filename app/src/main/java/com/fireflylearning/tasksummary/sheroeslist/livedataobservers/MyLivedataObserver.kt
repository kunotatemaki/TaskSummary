package com.fireflylearning.tasksummary.sheroeslist.livedataobservers

import com.fireflylearning.tasksummary.model.Task

/**
 * Created by Roll on 31/8/17.
 */
interface MyLivedataObserver {

    fun handleChangesInObservedSuperHeroes(superheroes: MutableList<Task>)

}