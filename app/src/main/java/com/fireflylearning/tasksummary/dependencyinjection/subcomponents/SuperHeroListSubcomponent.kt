package com.fireflylearning.tasksummary.dependencyinjection.subcomponents

import com.fireflylearning.tasksummary.dependencyinjection.modules.SuperHeroListModule
import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
import com.fireflylearning.tasksummary.sheroeslist.views.SuperHeroListActivity
import dagger.Subcomponent

/**
 * Created by Roll on 31/8/17.
 */
@CustomScopes.ActivityScope
@Subcomponent(modules = arrayOf(SuperHeroListModule::class))
interface SuperHeroListSubcomponent {
    fun inject(activity: SuperHeroListActivity)
}

