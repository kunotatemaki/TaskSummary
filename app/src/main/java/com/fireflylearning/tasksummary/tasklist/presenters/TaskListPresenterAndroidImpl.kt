package com.fireflylearning.tasksummary.tasklist.presenters

import android.support.annotation.VisibleForTesting
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
import com.fireflylearning.tasksummary.model.Task
import com.fireflylearning.tasksummary.network.logic.NetworkManager
import com.fireflylearning.tasksummary.resources.ResourcesManager
import com.fireflylearning.tasksummary.safe
import com.fireflylearning.tasksummary.tasklist.livedataobservers.MyLivedataObserver
import com.fireflylearning.tasksummary.tasklist.views.TaksListView
import com.fireflylearning.tasksummary.tasklist.views.TaskView
import java.lang.ref.WeakReference
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


    override fun loadSuperHeroes() {
        mView.safe {
            val myView = mView.get()!!

            if(myView.getLiveSuperHeroes().getLivedataValue()?.isEmpty() == false){
                //no hace falta forzar a la vista a cargar los datos de la caché, porque
                //el livedata notificará a su observador (this) de su último valor cuando se suscriba
                //así que se llamará a handle... y se actualizará ahí
                return@safe
            }else{
                mView.get()!!.showLoader()
                network.getListOfTasks(myView.getLiveSuperHeroes())
            }
        }
    }

    override fun handleChangesInObservedSuperHeroes(superheroes: MutableList<Task>) {
        mView.safe {
            val myView = mView.get()!!
            myView.hideLoader()
            if(superheroes.size == 0){
                myView.showEmptyList(resources.getString(R.string.no_superheroes))
            }else {
                mView.get()!!.setSuperHeroesInView(superheroes)
            }
        }
    }

    override fun superHeroClicked(superHeroView: TaskView, superHero: Task) {
        /*log.d(this, "pulsado: " + superHero.name)
        mView.safe {
            mView.get()!!.showSuperHeroDetails(superHeroView, superHero)
        }*/
    }
}