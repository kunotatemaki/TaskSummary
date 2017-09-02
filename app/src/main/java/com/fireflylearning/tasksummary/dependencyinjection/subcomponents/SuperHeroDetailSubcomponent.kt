package com.fireflylearning.tasksummary.dependencyinjection.subcomponents

import com.fireflylearning.tasksummary.dependencyinjection.modules.SuperHeroDetailModule
import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
import com.fireflylearning.tasksummary.sherodetails.views.SuperHeroDetailActivity
import dagger.Subcomponent

/**
 * Created by Roll on 31/8/17.
 */
@CustomScopes.ActivityScope
@Subcomponent(modules = arrayOf(SuperHeroDetailModule::class))
interface SuperHeroDetailSubcomponent {
    fun inject(activity: SuperHeroDetailActivity)
}