package com.fireflylearning.tasksummary.ui.tasklist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.databinding.FragmentTaskListBinding
import com.fireflylearning.tasksummary.persistence.entities.Task
import com.fireflylearning.tasksummary.ui.tasklist.adapters.TaskListAdapter
import com.fireflylearning.tasksummary.utils.FireflyConstants
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.vo.Resource
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [TaskListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    //TODO quitar esta inyección
    @Inject
    protected lateinit var adapter: TaskListAdapter

    @Inject
    lateinit var log: LoggerHelper

    private lateinit var mBinding: FragmentTaskListBinding

    private lateinit var taskViewModel: TaskListViewModel

    private lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        taskViewModel = ViewModelProviders.of(this, viewModelFactory).get(TaskListViewModel::class.java)
        //get token and host from intent
        taskViewModel.token = arguments.getString(FireflyConstants.SECRET_TOKEN)
        taskViewModel.host = arguments.getString(FireflyConstants.HOST)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_list, container, false)

        mBinding.showMessage = ViewModelProviders.of(this, viewModelFactory).get(TaskListViewModel::class.java).showingEmpty

        //set the mAdapter for the recycler view
        mRecyclerView = mBinding.taskList

        // use a linear layout manager
        val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.adapter = adapter
        //add a divider decorator
        val dividerItemDecoration = DividerItemDecoration(mRecyclerView.context,
                DividerItemDecoration.VERTICAL)
        mRecyclerView.addItemDecoration(dividerItemDecoration)

        taskViewModel.getResults().observe(this, Observer<Resource<PagedList<Task>>> { tasks ->
            log.d(this, "holaaaa: " + tasks?.data?.size)
        })

        taskViewModel.setQuery(date = System.currentTimeMillis())

        return mBinding.root
    }

    fun setUpFragment(){

    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param host Parameter 1.
         * @param token Parameter 2.
         * @return A new instance of fragment TaskListFragment.
         */

        fun newInstance(host: String, token: String): TaskListFragment {
            val fragment = TaskListFragment()
            val args = Bundle()
            args.putString(FireflyConstants.HOST, host)
            args.putString(FireflyConstants.SECRET_TOKEN, token)
            fragment.arguments = args
            return fragment
        }
    }
}
