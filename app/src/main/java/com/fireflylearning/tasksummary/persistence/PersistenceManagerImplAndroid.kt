package com.fireflylearning.tasksummary.persistence

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.fireflylearning.tasksummary.persistence.databases.FireflyDatabase
import com.fireflylearning.tasksummary.persistence.entities.Task
import javax.inject.Inject

/**
 * Created by Roll on 5/10/17.
 * Implementation of persistence manager for android
 */
class PersistenceManagerImplAndroid @Inject constructor(val db: FireflyDatabase): PersistenceManager {

    companion object {
        /**
         * A good page size is a value that fills at least a screen worth of content on a large
         * device so the User is unlikely to see a null item.
         * You can play with this constant to observe the paging behavior.
         * <p>
         * It's possible to vary this with list device size, but often unnecessary, unless a user
         * scrolling on a large device is expected to scroll through items more quickly than a small
         * device, such as when the large device uses a grid layout of items.
         */
        private const val PAGE_SIZE = 30

        /**
         * If placeholders are enabled, PagedList will report the full size but some items might
         * be null in onBind method (PagedListAdapter triggers a rebind when data is loaded).
         * <p>
         * If placeholders are disabled, onBind will never receive null but as more pages are
         * loaded, the scrollbars will jitter as new pages are loaded. You should probably disable
         * scrollbars if you disable placeholders.
         */
        private const val ENABLE_PLACEHOLDERS = true
    }

    override fun insertTask(task: Task) {
        db.taskDao().insertAll(task)
    }

    override fun insertListOfTasks(tasks: List<Task>) {
        db.taskDao().insertAll(*tasks.toTypedArray())
    }

    override fun loadTasks() : LiveData<PagedList<Task>> {
        return db.taskDao().getAllTasks().create(0,
                PagedList.Config.Builder()
                        .setPageSize(PAGE_SIZE)
                        .setEnablePlaceholders(ENABLE_PLACEHOLDERS)
                        .build())

    }
}