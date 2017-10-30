package com.fireflylearning.tasksummary.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import android.os.SystemClock
import com.fireflylearning.tasksummary.AppExecutors
import com.fireflylearning.tasksummary.R
import com.fireflylearning.tasksummary.network.ApiResponse
import com.fireflylearning.tasksummary.network.FireflyServiceFactory
import com.fireflylearning.tasksummary.network.model.TaskServerResponse
import com.fireflylearning.tasksummary.persistence.PersistenceManager
import com.fireflylearning.tasksummary.persistence.entities.Task
import com.fireflylearning.tasksummary.utils.FireflyConstants
import com.fireflylearning.tasksummary.utils.RateLimiter
import com.fireflylearning.tasksummary.utils.preferences.PreferencesManager
import com.fireflylearning.tasksummary.vo.Resource
import com.rukiasoft.utilslibrary.resources.ResourcesManager
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
constructor(private val appExecutors: AppExecutors,
            private val persistenceManager: PersistenceManager,
            private val fireflyServiceFactory: FireflyServiceFactory,
            private val resources: ResourcesManager,
            private val preferencesManager: PreferencesManager) {



    private val taskListRateLimit: RateLimiter = RateLimiter(10, TimeUnit.MINUTES)

    fun loadTasks(host: String, token: String): LiveData<Resource<PagedList<Task>>> {
        return object : NetworkBoundResource<PagedList<Task>, TaskServerResponse>(appExecutors) {
            override fun saveCallResult(item: TaskServerResponse) {
                val list: MutableList<Task> = arrayListOf()
                //store the last fetch date
                preferencesManager.setFetchDate(SystemClock.uptimeMillis())

                //map tasks to desired format value
                item.
                        tasks
                        ?.tasksList
                        ?.mapTo(list) {
                            Task(it)
                        }

                persistenceManager.insertListOfTasks(list)
            }

            override fun shouldFetch(data: PagedList<Task>?): Boolean {
                return data == null || data.isEmpty() || taskListRateLimit.shouldFetch(preferencesManager.getFetchDate())
            }

            override fun loadFromDb(): LiveData<PagedList<Task>> {
                return persistenceManager.loadTasks()
            }

            override fun createCall(): LiveData<ApiResponse<TaskServerResponse>> {
                val query = resources.getString(R.string.tasks_query)
                return fireflyServiceFactory.getFireflyService(host, true).getAllTasks(FireflyConstants.DEVICE_ID, token, query)
            }

            override fun onFetchFailed() {
                preferencesManager.deleteFetchDate()
            }

        }.asLiveData()
    }



}
