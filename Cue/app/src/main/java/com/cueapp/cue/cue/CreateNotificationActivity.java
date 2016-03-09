package com.cueapp.cue.cue;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.Spinner;

import com.firebase.client.Firebase;
import com.facebook.FacebookSdk;
import android.content.Intent;

import java.util.*;
import java.lang.*;

public class CreateNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Firebase.setAndroidContext(this);

        setContentView(R.layout.activity_create_notification);
        setSupportActionBar(toolbar);
        Spinner spinner = (Spinner) findViewById(R.id.inviteEdit);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.friends_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void submitReminder(View view) {

       /* Firebase reminderRef = new Firebase("https://cue-app.firebaseio.com/");

        EditText edit_title = (EditText) findViewById(R.id.editTitle);
        CharSequence edit_title_value = edit_title.getText();

        EditText edit_description = (EditText) findViewById(R.id.editDescription);
        CharSequence edit_description_value = edit_description.getText();

        EditText edit_date = (EditText) findViewById(R.id.editDate);
        CharSequence edit_date_value = edit_date.getText();

        EditText edit_time = (EditText) findViewById(R.id.editTime);
        CharSequence edit_time_value = edit_time.getText();

        EditText invite_edit = (EditText) findViewById(R.id.inviteEdit);
        String invite_edit_value = invite_edit.getText().toString();

        Reminder newReminder = new Reminder(edit_title_value, edit_description_value, edit_date_value, edit_time_value, invite_edit_value);

        Map<String, Reminder> users = new HashMap<String, Reminder> ();
        users.put(invite_edit_value, newReminder);

        reminderRef.setValue(users); */

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
