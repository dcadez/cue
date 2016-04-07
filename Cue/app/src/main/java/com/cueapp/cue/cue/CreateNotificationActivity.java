package com.cueapp.cue.cue;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.firebase.client.Firebase;
import com.facebook.FacebookSdk;
import android.content.Intent;
import android.widget.TimePicker;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateNotificationActivity extends AppCompatActivity {

    private EditText editDate;
    private EditText editTime;

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

        editDate = (EditText) findViewById(R.id.editDate);

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(CreateNotificationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                    /*      Your code   to get date and time    */
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });


        editTime = (EditText) findViewById(R.id.editTime);
        editTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(CreateNotificationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        editTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);//No 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

    }


//    EditText editDate;


//    editDate = findViewById(R.id.editDate);
//    editDate = (EditText) findViewById(R.id.editDate);


    public void submitReminder(View view) {

//        Firebase ref = new Firebase("https://cue-app.firebaseio.com/");

        //User below class to submit new reminder on Firebase
        Reminder test = new Reminder("Title", "Description", Calendar.getInstance().getTime(), "Time", "Contact");
        FirebaseHelper.getInstance().AddNewReminder(test);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}


//Calendar myCalendar = Calendar.getInstance();
//EditText editDate = (EditText)findViewById(R.id.editDate);
//
//DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
//
//    @Override
//    public void onDateSet(DatePicker view, int year, int monthOfYear,
//                          int dayOfMonth) {
//        // TODO Auto-generated method stub
//        myCalendar.set(Calendar.YEAR, year);
//        myCalendar.set(Calendar.MONTH, monthOfYear);
//        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//        updateLabel();
//    }
//};
//
//
////    mEditInit.setOnClickListener(new View.OnClickListener() {
////        @Override
////        public void onClick(View v) {
////            showDialog(DATEINIT_DIALOG);
////        }
////
////    });
//
//editDate.setOnClickListener(new View.OnClickListener() {
//
////        @Override
//public void onClick(View v) {
//        // TODO Auto-generated method stub
//        new DatePickerDialog(CreateNotificationActivity.this, date, myCalendar
//        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//        }
//        });
//
//private void updateLabel() {
//
//        String myFormat = "MM/dd/yy"; //In which you need put here
//        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//
//        editDate.setText(sdf.format(myCalendar.getTime()));
//        }