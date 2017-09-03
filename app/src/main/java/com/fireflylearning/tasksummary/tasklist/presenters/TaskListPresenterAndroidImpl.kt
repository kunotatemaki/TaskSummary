package com.fireflylearning.tasksummary.tasklist.presenters

import android.support.annotation.VisibleForTesting
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
import com.fireflylearning.tasksummary.model.Task
import com.fireflylearning.tasksummary.network.logic.NetworkManager
import com.fireflylearning.tasksummary.utils.resources.ResourcesManager
import com.fireflylearning.tasksummary.safe
import com.fireflylearning.tasksummary.tasklist.livedataobservers.MyLivedataObserver
import com.fireflylearning.tasksummary.tasklist.views.TaksListView
import java.lang.ref.WeakReference
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Roll on 31/8/17.
 */
@CustomScopes.ActivityScope
class TaskListPresenterAndroidImpl @Inject constructor(val mView: WeakReference<TaksListView>)
    : TaskListPresenter, MyLivedataObserver {

    @Inject
    lateinit var log: LoggerHelper

    @Inject
    lateinit var network: NetworkManager

    @Inject
    lateinit var resources: ResourcesManager

    @VisibleForTesting
    constructor(resources: ResourcesManager, log: LoggerHelper, mView: WeakReference<TaksListView>,
                network: NetworkManager) : this(mView) {
        this.resources = resources
        this.log = log
        this.network = network

    }


    override fun loadTasks() {
        mView.safe {
            val myView = mView.get()!!

            if(myView.getLiveTaks().getLivedataValue()?.isEmpty() == false){
                //no hace falta forzar a la vista a cargar los datos de la caché, porque
                //el livedata notificará a su observador (this) de su último valor cuando se suscriba
                //así que se llamará a handle... y se actualizará ahí
                return@safe
            }else{
                mView.get()!!.showLoader()
                network.getListOfTasks(myView.getLiveTaks())
            }
        }
    }

    override fun handleChangesInObservedTasks(tasks: MutableList<Task>) {
        mView.safe {
            val myView = mView.get()!!
            myView.hideLoader()
            if(tasks.size == 0){
                myView.showEmptyList(resources.getString(R.string.no_superheroes))
            }else {
                mView.get()!!.setTasksInView(tasks)
            }
        }
    }

    override fun taskClicked(task: Task) {
        log.d(this, "clicked: " + task.title)
        /*mView.safe {
            mView.get()!!.showTaskDetails(superHeroView, superHero)
        }*/
    }
}