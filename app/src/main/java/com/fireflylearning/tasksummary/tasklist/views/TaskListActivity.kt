package com.fireflylearning.tasksummary.tasklist.views

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.fireflylearning.tasksummary.FireflyApp
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.databinding.ActivityMainBinding
import com.fireflylearning.tasksummary.dependencyinjection.modules.TaskListModule
import com.fireflylearning.tasksummary.dependencyinjection.scopes.CustomScopes
import com.fireflylearning.tasksummary.model.CustomLiveData
import com.fireflylearning.tasksummary.model.Task
import com.fireflylearning.tasksummary.tasklist.adapters.TaskListAdapter
import com.fireflylearning.tasksummary.tasklist.lifecycleobservers.TaskListLifecycleObserver
import com.fireflylearning.tasksummary.tasklist.presenters.TaskListPresenter
import com.fireflylearning.tasksummary.tasklist.viewmodels.TaskListViewModel
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.utils.ui.BaseActivity
import javax.inject.Inject


@CustomScopes.ActivityScope
class TaskListActivity : BaseActivity(), TaksListView {

    @Inject
    lateinit var presenter: TaskListPresenter

    @Inject
    lateinit var observer: TaskListLifecycleObserver

    @Inject
    lateinit var log: LoggerHelper

    @Inject
    protected lateinit var adapter: TaskListAdapter

    private lateinit var mRecyclerView: RecyclerView

    private lateinit var mBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //region DEPENDENCY INJECTION
        (application as FireflyApp).mComponent.getSuperHeroListSubcomponent(TaskListModule(this))
                .inject(this)
        //endregion


        //region DATA BINDING
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //endregion

        mBinding.showMessage = ViewModelProviders.of(this).get(TaskListViewModel::class.java).showingEmpty

        //set the mAdapter for the recycler view
        mRecyclerView = mBinding.superheroList

        // use a linear layout manager
        val mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.adapter = adapter
        //add a divider decorator
        val dividerItemDecoration = DividerItemDecoration(mRecyclerView.context,
                DividerItemDecoration.VERTICAL)
        mRecyclerView.addItemDecoration(dividerItemDecoration)

    }


    //region SUPERHEROLISTVIEW INTERFACE
    override fun addLifecycleObserver(observer: TaskListLifecycleObserver) {
        if(observer is LifecycleObserver){
            lifecycle.addObserver(observer)
        }
    }

    override fun getLiveSuperHeroes(): CustomLiveData<MutableList<Task>> {
        return ViewModelProviders.of(this).get(TaskListViewModel::class.java).superheroes
    }

    override fun setSuperHeroesInView(superHeroes: List<Task>) {
        log.d(this, "show superheroes in view")
        adapter.superHeroes.clear()
        adapter.superHeroes.addAll(superHeroes)
        adapter.notifyDataSetChanged()
    }

    override fun hideEmptyList() {
        ViewModelProviders.of(this).get(TaskListViewModel::class.java).showingEmpty = false
        mBinding.errorMessage.visibility = View.INVISIBLE
    }

    override fun showEmptyList(message: String) {
        ViewModelProviders.of(this).get(TaskListViewModel::class.java).showingEmpty = true
        mBinding.errorMessage.visibility = View.VISIBLE

    }

    override fun showLoader() {
        //hide error message because I'm gonna load data
        hideEmptyList()
        mBinding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        mBinding.progressBar.visibility = View.INVISIBLE
    }

    override fun showSuperHeroDetails(superHeroView: TaskView, superhero: Task) {
        /*val intent = Intent(this, SuperHeroDetailActivity::class.java)
        intent.putExtra(FireflyConstants.SUPERHERO, superhero)
        //trainsition
        if(superHeroView is View) {
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, superHeroView, getString(R.string.activity_image_trans))
            startActivity(intent, options.toBundle())
        }else {
            startActivity(intent)
        }*/
    }

    @VisibleForTesting
    fun setSuperHeroesList(superHeroes: MutableList<Task>){
        getLiveSuperHeroes().setLivedataValue(superHeroes)
    }

    //endregion
}
