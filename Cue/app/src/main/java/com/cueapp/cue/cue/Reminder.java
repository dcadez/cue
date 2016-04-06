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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.firebase.client.Firebase;
import com.facebook.FacebookSdk;
import android.content.Intent;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Reminder {
    private String reminderTitle;
    private String reminderDescription;
    private Date reminderDate;
    private String reminderTime;
    private String reminderContact;

    public Reminder() {}

    public Reminder(String reminderTitle, String reminderDescription, Date reminderDate, String reminderTime, String reminderContact) {
        this.reminderTitle = reminderTitle;
        this.reminderDescription = reminderDescription;
        this.reminderDate = reminderDate;
        this.reminderTime = reminderTime;
        this.reminderContact = reminderContact;
    }

    public String getTitle() {
        return reminderTitle;
    }

    public String getDescription() {
        return reminderDescription;
    }

    public Date getDate() {
        return reminderDate;
    }

    public String getTime() {
        return reminderTime;
    }

    public String getContact() {
        return reminderContact;
    }
}