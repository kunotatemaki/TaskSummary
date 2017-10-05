package com.fireflylearning.tasksummary.repository

import android.arch.lifecycle.LiveData
import com.fireflylearning.tasksummary.AppExecutors
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.network.ApiResponse
import com.fireflylearning.tasksummary.network.FireflyService
import com.fireflylearning.tasksummary.network.FireflyServiceFactory
import com.fireflylearning.tasksummary.network.model.TaskServerResponse
import com.fireflylearning.tasksummary.persistence.daos.TaskDao
import com.fireflylearning.tasksummary.persistence.databases.FireflyDatabase
import com.fireflylearning.tasksummary.persistence.entities.Task
import com.fireflylearning.tasksummary.utils.FireflyConstants
import com.fireflylearning.tasksummary.utils.RateLimiter
import com.fireflylearning.tasksummary.utils.resources.ResourcesManager
import com.fireflylearning.tasksummary.vo.Resource
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Roll on 4/10/17.
 * Repository that handles Task instances.
 *
 * Task - value object name
 * Repository - type of this class.
 */

@Singleton
class TaskRepository @Inject
constructor(private val appExecutors: AppExecutors, private val db: FireflyDatabase,
            private val fireflyServiceFactory: FireflyServiceFactory, private val resources: ResourcesManager) {



    private val taskListRateLimit: RateLimiter<String> = RateLimiter(1, TimeUnit.MINUTES)

    fun loadRepos(host: String, token: String): LiveData<Resource<List<Task>>> {
        return object : NetworkBoundResource<List<Task>, TaskServerResponse>(appExecutors) {
            override fun saveCallResult(item: TaskServerResponse) {
                val list: MutableList<Task> = arrayListOf()

                //map tasks to desired format value
                item.
                        tasks
                        ?.tasksList
                        ?.mapTo(list) {
                            Task(it)
                        }

                db.taskDao().insertAll(*list.toTypedArray())
            }

            override protected fun shouldFetch(data: List<Task>?): Boolean {
                return data == null || data.isEmpty() || taskListRateLimit.shouldFetch(token)
            }

            override protected fun loadFromDb(): LiveData<List<Task>> {
                return db.taskDao().getAllTasks()
            }

            override protected fun createCall(): LiveData<ApiResponse<TaskServerResponse>> {
                val query = resources.getString(R.string.tasks_query)
                return fireflyServiceFactory.getFireflyService(host).getAllTasks(FireflyConstants.DEVICE_ID, token, query)
            }

            override protected fun onFetchFailed() {
                taskListRateLimit.reset(token)
            }

        }.asLiveData()
    }



}
