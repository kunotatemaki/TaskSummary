package com.fireflylearning.tasksummary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;

import com.fireflylearning.tasksummary.taskdetails.ActivityDetails;
import com.fireflylearning.tasksummary.ui.tasklist.views.TaskListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.runner.lifecycle.Stage.RESUMED;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Roll on 1/9/17.
 */


@RunWith(AndroidJUnit4.class)
public class ViewTest {

    @Rule
    public MyCustomActivityRule<TaskListActivity> mActivityRule =
            new MyCustomActivityRule<TaskListActivity>(TaskListActivity.class){
                @Override
                protected Intent getActivityIntent() {
                    Context targetContext = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();
                    Intent result = new Intent(targetContext, TaskListActivity.class);
                    result.putExtra("host", "host");
                    result.putExtra("secret_token", "secret_token");

                    return result;
                }
            };




    @Test
    public void clickAddTaskButton_launchAddTaskActivity() {
        // Type text and then press the button.
        Espresso.onView(ViewMatchers.withId(R.id.taskList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //Espresso.onView(ViewMatchers.withId(R.id.superhero_list_item)).perform(click());
        Activity activity = getActivityInstance();
        boolean b = (activity instanceof ActivityDetails);
        assertTrue(b);
    }

    public Activity getActivityInstance() {
        final Activity[] activity = new Activity[1];
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable( ) {
            public void run() {
                Activity currentActivity;
                Collection resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(RESUMED);
                if (resumedActivities.iterator().hasNext()){
                    currentActivity = (Activity) resumedActivities.iterator().next();
                    activity[0] = currentActivity;
                }
            }
        });

        return activity[0];
    }
}
