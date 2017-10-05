package com.fireflylearning.tasksummary

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import java.lang.ref.WeakReference

/**
 * Created by Roll on 31/8/17.
 */


/***
 * this function allows to execute some function only if the value of weak reference is not null
 * to avoid checking if not null, or having to use ?
 */
fun <T> WeakReference<T>.safe(body : T.() -> Unit) {
    this.get()?.body()
}


fun <X, Y> LiveData<X>.switchMap(func: (X) -> LiveData<Y>)
        = Transformations.switchMap(this, func)