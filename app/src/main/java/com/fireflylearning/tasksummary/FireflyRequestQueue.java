package com.fireflylearning.tasksummary;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import android.content.Context;


/**
 * Created by Ben on 11/07/2017.
 */

public class FireflyRequestQueue {

    private static FireflyRequestQueue mInstance;

    private RequestQueue mRequestQueue;
    private String mHost;

    public static void initialise(Context context, String host)
    {
        mInstance = new FireflyRequestQueue();
        mInstance.mHost = host;
        mInstance.mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized FireflyRequestQueue getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public String buildUrl(String postfix) {
        return "https://" + mHost + "/" + postfix;
    }
}
