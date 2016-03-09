/**
 * Created by Ryan on 3/8/2016.
 */
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
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.facebook.FacebookSdk;
import android.content.Intent;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Reminder {
    private CharSequence reminderTitle;
    private CharSequence reminderDescription;
    private CharSequence reminderDate;
    private CharSequence reminderTime;
    private String reminderContact;

    public Reminder() {}

    public Reminder(CharSequence reminderTitle, CharSequence reminderDescription, CharSequence reminderDate, CharSequence reminderTime, String reminderContact) {
        this.reminderTitle = reminderTitle;
        this.reminderDescription = reminderDescription;
        this.reminderDate = reminderDate;
        this.reminderTime = reminderTime;
        this.reminderContact = reminderContact;
    }

    public CharSequence getTitle() {
        return reminderTitle;
    }

    public CharSequence getDescription() {
        return reminderDescription;
    }

    public CharSequence getDate() {
        return reminderDate;
    }

    public CharSequence reminderTime() {
        return reminderTime;
    }

    public CharSequence getContact() {
        return reminderContact;
    }
}