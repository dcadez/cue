package com.cueapp.cue.cue;

import android.util.Log;

import com.facebook.AccessToken;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Prateek on 16/03/16.
 *  firebase user login Id: eecs441cue@gmail.com
 *  firebase pass: justgoforit
 */

public class FirebaseHelper {

    Firebase mRef;
    Firebase mUserReminders;
    Firebase mLoggedInUserReminders;
    String userId="User1";

    private static FirebaseHelper ourInstance = new FirebaseHelper();

    public static FirebaseHelper getInstance() {
        return ourInstance;
    }

    private FirebaseHelper() {
        UpdateFirebaseSettings();
    }

    void UpdateFirebaseSettings()
    {
        mRef = new Firebase("https://luminous-inferno-3119.firebaseio.com/MainDB");
        mUserReminders = mRef.child("UserReminders");
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        mLoggedInUserReminders = mUserReminders.child(accessToken.getUserId());

        AddNewReminderEventHandler();
    }

    void AddNewReminderEventHandler(){
        mLoggedInUserReminders.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Reminder newReminder = dataSnapshot.getValue(Reminder.class);

//                lastReminderId = dataSnapshot.getKey().toString();
//                Reminder test = new Reminder("Title2", "Description2", Calendar.getInstance().getTime(), "Time2", "Contact2");
//                UpdateReminder(test);

                Database.getInstance().AddNewReminder(newReminder);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.v("new reminder","new reminder updated");
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    public void AddNewReminder(Reminder newReminder)
    {
        //Todo: Lood for each user and it into their reminder list
        Firebase newFirebaseReminder = mLoggedInUserReminders.push();
        newReminder.SetId(newFirebaseReminder.getKey().toString());
        newFirebaseReminder.setValue(newReminder);
    }
String lastReminderId;
    public void UpdateReminder(Reminder currentReminder)
    {
        Map<String,Object> reminder = new HashMap<String,Object>();
        reminder.put("title",currentReminder.getTitle());
        reminder.put("contact",currentReminder.getContact());
        reminder.put("date",currentReminder.getContact());
        reminder.put("description",currentReminder.getDescription());

        Firebase existingReminder = mLoggedInUserReminders.child(lastReminderId);
        existingReminder.updateChildren(reminder);
    }
}
