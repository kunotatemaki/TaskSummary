package com.fireflylearning.tasksummary.tasklist.views

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.design.widget.Snackbar
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.di.interfaces.CustomScopes
import com.fireflylearning.tasksummary.login.views.LoginActivity
import com.fireflylearning.tasksummary.model.CustomLiveData
import com.fireflylearning.tasksummary.persistence.entities.Task
import com.fireflylearning.tasksummary.taskdetails.ActivityDetails
import com.fireflylearning.tasksummary.tasklist.TaskListNavigationController
import com.fireflylearning.tasksummary.tasklist.adapters.TaskListAdapter
import com.fireflylearning.tasksummary.tasklist.lifecycleobservers.TaskListLifecycleObserver
import com.fireflylearning.tasksummary.tasklist.presenters.TaskListPresenter
import com.fireflylearning.tasksummary.tasklist.TaskListViewModel
import com.fireflylearning.tasksummary.utils.FireflyConstants
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.utils.preferences.PreferencesManager
import com.fireflylearning.tasksummary.utils.ui.BaseActivity
import com.fireflylearning.tasksummary.vo.Resource
import javax.inject.Inject


@CustomScopes.ActivityScope
class TaskListActivity : BaseActivity(), TaskListView {

    @Inject
    lateinit var navigationController: TaskListNavigationController

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var presenter: TaskListPresenter

    /*@Inject
    lateinit var observer: TaskListLifecycleObserver*/

    @Inject
    lateinit var log: LoggerHelper

    @Inject
    protected lateinit var adapter: TaskListAdapter

    @Inject
    lateinit var preferences: PreferencesManager


    private lateinit var taskViewModel: TaskListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        setContentView(R.layout.activity_task_list)


        if(savedInstanceState == null){
            navigationController.navigateToListOfTasks(intent.extras[FireflyConstants.HOST] as String,
                    intent.extras[FireflyConstants.SECRET_TOKEN] as String)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.task_list_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.close_sesion -> {
                log.d(this, "cerrar sesion")
                presenter.closeSession()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //region SUPERHEROLISTVIEW INTERFACE
    override fun addLifecycleObserver(observer: TaskListLifecycleObserver) {
        if(observer is LifecycleObserver){
            lifecycle.addObserver(observer)
        }
    }

    override fun getLiveTaks(): CustomLiveData<MutableList<Task>> {
        return ViewModelProviders.of(this, viewModelFactory).get(TaskListViewModel::class.java).tasks
    }

    override fun setTasksInView(tasks: List<Task>) {
        log.d(this, "show tasks in view")
        adapter.tasks.clear()
        adapter.tasks.addAll(tasks)
        adapter.notifyDataSetChanged()
    }

    override fun hideEmptyList() {
        ViewModelProviders.of(this, viewModelFactory).get(TaskListViewModel::class.java).showingEmpty = false
        //mBinding.errorMessage.visibility = View.INVISIBLE
    }

    override fun showEmptyList(message: String) {
        ViewModelProviders.of(this, viewModelFactory).get(TaskListViewModel::class.java).showingEmpty = true
        //mBinding.errorMessage.visibility = View.VISIBLE

    }

    override fun showLoader() {
        //hide error message because I'm gonna load data
        hideEmptyList()
        //mBinding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        //mBinding.progressBar.visibility = View.INVISIBLE
    }

    override fun showTaskDetails(url: String) {
        val intent = Intent(this, ActivityDetails::class.java)
        intent.putExtra(FireflyConstants.URL, url)
        startActivity(intent)
    }

    override fun showMessage(message: String) {
        //Snackbar.make(mBinding.root, message, Snackbar.LENGTH_LONG).show()
    }

    override fun getTokenFromChache(): String {
        return ViewModelProviders.of(this, viewModelFactory).get(TaskListViewModel::class.java).token
    }

    override fun getHostFromChache(): String {
        return ViewModelProviders.of(this, viewModelFactory).get(TaskListViewModel::class.java).host
    }

    override fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }

    @VisibleForTesting
    fun setTaskList(tasks: MutableList<Task>){
        getLiveTaks().setLivedataValue(tasks)
    }


    //endregion
}
