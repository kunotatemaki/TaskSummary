package com.fireflylearning.tasksummary;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Ben on 14/07/2017.
 */

public class Task {
    public int id;
    public String title;
    public Date set;
    public Boolean archived;
    public Boolean draft;
    public Boolean show_in_markbook;
    public Boolean highlight_in_markbook;
    public Boolean show_in_parent_portal;
    public Boolean hide_addressees;

    public String toFlagsString()
    {
        ArrayList<String> flags = new ArrayList<String>();

        if(archived)
            flags.add("archived");

        if(draft)
            flags.add("draft");

        if(show_in_markbook && highlight_in_markbook)
            flags.add("markbook (highlighted)");
        else if(show_in_markbook)
            flags.add("markbook");

        if(show_in_parent_portal)
            flags.add("parents");

        if(hide_addressees)
            flags.add("no addressees");

        return android.text.TextUtils.join(", ", flags);
    }
}
