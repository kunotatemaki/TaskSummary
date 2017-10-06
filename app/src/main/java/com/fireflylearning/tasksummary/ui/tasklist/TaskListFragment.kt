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
import com.fireflylearning.tasksummary.ui.tasklist.TaskListAdapter.TaskClickCallback
import com.fireflylearning.tasksummary.utils.FireflyConstants
import com.fireflylearning.tasksummary.utils.TaskUtils
import com.fireflylearning.tasksummary.utils.logger.LoggerHelper
import com.fireflylearning.tasksummary.utils.resources.ResourcesManager
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

    @Inject
    lateinit var log: LoggerHelper

    @Inject
    lateinit var resourcesManager: ResourcesManager

    @Inject
    lateinit var taskUtils: TaskUtils

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


        setUpFragment()





        return mBinding.root
    }

    fun setUpFragment(){
        //get the recycler
        mRecyclerView = mBinding.taskList

        // use a linear layout manager
        val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mRecyclerView.layoutManager = mLayoutManager

        //add a divider decorator
        val dividerItemDecoration = DividerItemDecoration(mRecyclerView.context,
                DividerItemDecoration.VERTICAL)
        mRecyclerView.addItemDecoration(dividerItemDecoration)

        //add the adapter
        val adapter = TaskListAdapter(
                taskUtils,
                resourcesManager,
                object : TaskClickCallback {
                    override fun onClick(task: Task?) {
                        log.d(this, "clicked")
                    }
                }
        )

        mRecyclerView.adapter = adapter


        taskViewModel.getResults().observe(this, Observer<Resource<PagedList<Task>>> { tasks ->
            log.d(this, "holaaaa: " + tasks?.data?.size)
            mBinding.taskCount = tasks?.data?.size
            mBinding.resource = tasks
            adapter.setList(tasks?.data)
        })

        /*searchViewModel.getResults().observe(this, { result ->
            binding.get().setSearchResource(result)
            binding.get().setResultCount(if (result == null || result!!.data == null)
                0
            else
                result!!.data.size())
            adapter.get().replace(if (result == null) null else result!!.data)
            binding.get().executePendingBindings()
        })*/

        //TODO hacer que no lo pida siempre
        taskViewModel.setQuery(date = System.currentTimeMillis())

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
