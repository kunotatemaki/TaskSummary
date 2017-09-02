package com.fireflylearning.tasksummary.dependencyinjection.components

import com.fireflylearning.tasksummary.dependencyinjection.modules.FintonicAppModule
import com.fireflylearning.tasksummary.dependencyinjection.modules.SuperHeroDetailModule
import com.fireflylearning.tasksummary.dependencyinjection.modules.SuperHeroListModule
import com.fireflylearning.tasksummary.dependencyinjection.subcomponents.SuperHeroDetailSubcomponent
import com.fireflylearning.tasksummary.dependencyinjection.subcomponents.SuperHeroListSubcomponent
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Roll on 31/8/17.
 */
@Singleton
@Component(modules = arrayOf(FintonicAppModule::class))
interface FintonicAppComponent {
    fun getSuperHeroListSubcomponent(module: SuperHeroListModule): SuperHeroListSubcomponent
    fun getSuperHeroDetailSubcomponent(module: SuperHeroDetailModule): SuperHeroDetailSubcomponent

}