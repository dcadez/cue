package com.cueapp.cue.cue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dominic on 4/6/2016.
 */
public class ReminderAdapter extends ArrayAdapter<Reminder> {

    public ReminderAdapter(Context context, ArrayList<Reminder> reminders) {
        super(context, 0, reminders);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Reminder reminder = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.reminder_list_item, parent, false);
        }
        // Lookup view for data population
        TextView reminderTitle = (TextView) convertView.findViewById(R.id.reminder_item_title);
        TextView reminderSender = (TextView) convertView.findViewById(R.id.reminder_item_sender);
        TextView reminderDate = (TextView) convertView.findViewById(R.id.reminder_item_date);
        // Populate the data into the template view using the data object
        reminderTitle.setText(reminder.getTitle());
        reminderSender.setText(reminder.getContact());
        reminderDate.setText(reminder.getTime());
        // Return the completed view to render on screen
        return convertView;

    }


}
