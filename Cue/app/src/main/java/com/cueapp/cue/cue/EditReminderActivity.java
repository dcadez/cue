package com.cueapp.cue.cue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EditReminderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reminder);
    }

    public void saveChanges(View view) {
        Intent intent = new Intent(this, ViewReminderActivity.class);
        startActivity(intent);
    }

    public void cancelChanges(View view) {
        Intent intent = new Intent(this, ViewReminderActivity.class);
        startActivity(intent);
    }
}
