package com.fireflylearning.tasksummary;

import android.app.ListActivity;
import android.os.Bundle;

import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

public class TaskListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        FireflyRequestQueue.getInstance().RunGraphqlQuery(
                "query Query { tasks(set_by:\"DB:Cloud:DB:SIMSemp:89\") {id, title} }",
                new Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String responseRaw = response.toString();
                        String responseRaw2 = response.toString();
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
    }

    private void buildTaskList() {

    }
}
