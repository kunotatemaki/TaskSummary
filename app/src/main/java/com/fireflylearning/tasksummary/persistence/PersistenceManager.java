package com.fireflylearning.tasksummary.persistence;


import com.fireflylearning.tasksummary.model.CustomLiveData;
import com.fireflylearning.tasksummary.persistence.entities.Task;

import java.util.List;

/**
 * Created by Roll on 11/8/17.
 */

public interface PersistenceManager {

    void insertTask(Task task);

    void insertListOfTasks(List<Task> tasks);

    void loadTasks(CustomLiveData<List<Task>> liveTasks);

}
