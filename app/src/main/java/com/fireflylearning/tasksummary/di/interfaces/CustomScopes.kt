package com.fireflylearning.tasksummary.di.interfaces

import javax.inject.Scope

/**
 * Created by Roll on 31/8/17.
 */
interface CustomScopes {

    @Scope
    annotation class ActivityScope

    @Scope
    annotation class FragmentScope

}