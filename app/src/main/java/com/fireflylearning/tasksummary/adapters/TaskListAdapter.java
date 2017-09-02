package com.fireflylearning.tasksummary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fireflylearning.tasksummary.R;
import com.fireflylearning.tasksummary.objects.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Ben on 14/07/2017.
 */

public class TaskListAdapter extends ArrayAdapter<Task> {

    private static class ViewHolder {
        TextView title;
        TextView date;
        TextView flags;
    }

    public TaskListAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View taskView;
        ViewHolder viewHolder;

        if (convertView == null) {
            taskView = LayoutInflater.from(this.getContext()).inflate(R.layout.activity_task_list_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = taskView.findViewById(R.id.title);
            viewHolder.flags = taskView.findViewById(R.id.flags);
            viewHolder.date = taskView.findViewById(R.id.date_set);
            taskView.setTag(viewHolder);
        } else {
            taskView = convertView;
            viewHolder = (ViewHolder)taskView.getTag();
        }

        Task task = getItem(position);

        if (task != null) {

            viewHolder.title.setText(task.title);
            viewHolder.flags.setText(task.toFlagsString());

            SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE d MMM yyyy, h:mm a");
            viewHolder.date.setText(dateFormatter.format(task.set));
        }

        return taskView;
    }
}
