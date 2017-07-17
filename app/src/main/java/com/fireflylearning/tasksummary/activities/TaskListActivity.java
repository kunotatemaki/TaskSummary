package com.fireflylearning.tasksummary.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.fireflylearning.tasksummary.FireflyRequestQueue;
import com.fireflylearning.tasksummary.R;
import com.fireflylearning.tasksummary.objects.Task;
import com.fireflylearning.tasksummary.adapters.TaskListAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TaskListActivity extends AppCompatActivity {

    TextView emptyTextView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_task_list);

        emptyTextView = (TextView)findViewById(R.id.empty);
        listView = (ListView)findViewById(R.id.list);

        showStatus(true, R.string.task_list_loading);

        FireflyRequestQueue.getInstance().RunGraphqlQuery(
                R.string.tasks_query,
                new Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            buildTaskList(response.getJSONArray("tasks"));
                        } catch (JSONException e) {
                            showDataError(e.getLocalizedMessage());
                        }
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showDataError(error.getLocalizedMessage());
                    }
                });
    }

    private void buildTaskList(JSONArray data) {

        Gson gson = new Gson();

        ArrayList<Task> taskArray = gson.fromJson(data.toString(), new TypeToken<ArrayList<Task>>() {}.getType());

        Collections.sort(taskArray, new TaskSetComparator());

        showStatus(taskArray.size() == 0, R.string.task_list_no_tasks);

        TaskListAdapter adapter = new TaskListAdapter(this, taskArray);

        listView.setAdapter(adapter);
    }

    private void showDataError(String message) {
        showStatus(true, getBaseContext().getResources().getString(R.string.task_list_error) + ": " + message);
    }

    private void showStatus(Boolean visible, int statusResourceId) {
        showStatus(visible, getResources().getString(statusResourceId));
    }

    private void showStatus(Boolean visible, String status) {

        emptyTextView.setText(status);

        if(visible) {
            emptyTextView.setVisibility(View.VISIBLE);
        } else {
            emptyTextView.setVisibility(View.GONE);
        }
    }

    public class TaskSetComparator implements Comparator<Task>
    {
        public int compare(Task left, Task right) {
            return left.set.compareTo(right.set);
        }
    }
}
