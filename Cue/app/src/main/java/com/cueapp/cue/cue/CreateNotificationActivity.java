package com.cueapp.cue.cue;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.facebook.FacebookSdk;
import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_create_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    EditText title;
    EditText description;
    EditText date;
    EditText time;
    EditText contact;

    public void submitReminder(View view) {

        title = (EditText)findViewById(R.id.editTitle);
        description = (EditText)findViewById(R.id.editDescription);
        date = (EditText)findViewById(R.id.editDate);
        time = (EditText)findViewById(R.id.editTime);

        Reminder test = new Reminder(title.getText().toString(), description.getText().toString(),
                Calendar.getInstance().getTime(), "Time", "Contact");


//        Firebase ref = new Firebase("https://cue-app.firebaseio.com/");

        //User below class to submit new reminder on Firebase

        FirebaseHelper.getInstance().AddNewReminder(test);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
