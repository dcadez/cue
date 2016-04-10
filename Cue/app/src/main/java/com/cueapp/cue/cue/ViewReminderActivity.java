package com.cueapp.cue.cue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ViewReminderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reminder);
    }

    public void editReminder(View view) {
        Intent intent = new Intent(this, EditReminderActivity.class);
        startActivity(intent);
    }

    public void deleteReminder(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
