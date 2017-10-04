package com.fireflylearning.tasksummary.di.interfaces

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass

/**
 * Created by Roll on 4/10/17.
 * key for viewmodels
 */

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)