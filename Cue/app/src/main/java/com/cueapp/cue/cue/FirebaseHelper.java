package com.cueapp.cue.cue;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Calendar;
import java.util.Date;

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
        mLoggedInUserReminders = mUserReminders.child(userId);

        AddNewReminderEventHandler();
    }

    void AddNewReminderEventHandler(){
        mLoggedInUserReminders.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                com.cueapp.cue.cue.Reminder newReminder = dataSnapshot.getValue(
                        com.cueapp.cue.cue.Reminder.class);
//                Log.v("New Value in Reminder",newReminder.getTitle());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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


    public void AddNewReminder(com.cueapp.cue.cue.Reminder newReminder)
    {
        //Todo: Lood for each user and it into their reminder list
        Firebase newFirebaseReminder = mLoggedInUserReminders.push();
        newFirebaseReminder.setValue(newReminder);
    }

}
