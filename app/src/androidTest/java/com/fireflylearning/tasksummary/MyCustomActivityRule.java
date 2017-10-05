package com.fireflylearning.tasksummary;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import com.fireflylearning.tasksummary.persistence.entities.Task;
import com.fireflylearning.tasksummary.ui.tasklist.views.TaskListActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Roll on 1/9/17.
 */

public class MyCustomActivityRule<A extends TaskListActivity> extends ActivityTestRule<A> {
    public MyCustomActivityRule(Class<A> activityClass) {
        super(activityClass);
    }

    @Override
    protected void beforeActivityLaunched() {
        super.beforeActivityLaunched();
        // Maybe prepare some mock service calls
        // Maybe override some depency injection modules with mocks

    }

    @Override
    protected Intent getActivityIntent() {
        Intent customIntent = new Intent();
        // add some custom extras and stuff
        return customIntent;
    }


    @Override
    protected void afterActivityLaunched() {
        super.afterActivityLaunched();
        final List<Task> list = new ArrayList<>();
        Task task = new Task(1,"task 1", "description", new Date(), new Date(),
                true, true, true, false,
                false, true);
        list.add(task);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getActivity().setTaskList(list);
            }
        });

        // maybe you want to do something here
    }

    @Override
    protected void afterActivityFinished() {
        super.afterActivityFinished();
        // Clean up mocks
    }


}
