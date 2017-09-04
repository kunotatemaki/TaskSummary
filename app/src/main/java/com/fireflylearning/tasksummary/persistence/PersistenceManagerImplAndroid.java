package com.fireflylearning.tasksummary.persistence;

import android.os.AsyncTask;

import com.fireflylearning.tasksummary.FireflyApp;
import com.fireflylearning.tasksummary.model.CustomLiveData;
import com.fireflylearning.tasksummary.persistence.entities.Task;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Roll on 11/8/17.
 */

@Singleton
public class PersistenceManagerImplAndroid implements PersistenceManager {

    @Inject
    FireflyApp mApplication;

    @Inject
    public PersistenceManagerImplAndroid() {
    }

    private static AsyncTask<Void, Void, Void> insertTask;

    @Override
    public void insertTask(final Task task) {
        insertTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                mApplication.mDatabase.taskDao().insertAll(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void param) {

            }
        };
        insertTask.execute();
    }

    @Override
    public void insertListOfTasks(final List<Task> tasks) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                mApplication.mDatabase.taskDao().insertAll(tasks.toArray(new Task[tasks.size()]));
                return null;
            }

            @Override
            protected void onPostExecute(Void param) {

            }
        }.execute();
    }

    @Override
    public void loadTasks(final CustomLiveData<List<Task>> liveTasks) {
        new AsyncTask<Void, Void, List<Task>>() {
            @Override
            protected List<Task> doInBackground(Void... params) {
                return mApplication.mDatabase.taskDao().getAll();
            }

            @Override
            protected void onPostExecute(List<Task> tasks) {
                liveTasks.forceStorageInLocalDatabaseOnNewData(false);
                List<Task> taskList = liveTasks.getLivedataValue();
                if(taskList == null){
                    taskList = new ArrayList<>();
                }
                taskList.addAll(tasks);
                liveTasks.setLivedataValue(taskList);

            }
        }.execute();
    }


}
