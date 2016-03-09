package com.cueapp.cue.cue;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.facebook.FacebookSdk;
import com.firebase.client.FirebaseError;

import android.content.Intent;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static CallbackManager callbackManager;
    private static boolean logged_in = false;
    private ListView lv;

    public class Notification{
        public String message;
        public String from;
        public String time;
        public String title;

        public String toString(){
            return title + "\n\t" + message + "\n\t" + from + "\n\t" + time + "\n";
        }
    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        if (!logged_in) {
//            logged_in = true;
//            Intent intent = new Intent(this, FacebookLoginActivity.class);
//            startActivity(intent);
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        toolbar.setTitle("Cue");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        // Populate listView
        lv = (ListView) findViewById(R.id.listView);

//        List<String> your_array_list = new ArrayList<String>();
//        your_array_list.add("Take out trash    Ryan Carrell  5:40 PM");
//        your_array_list.add("Get Eggs from Kroger    Me      Feb 22");
//
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_list_item_1,
//                your_array_list );

        // Instanciating an array list (you don't need to do this,
        // you already have yours).
        List<Notification> your_array_list = new ArrayList<Notification>();
        Notification notification = new Notification();
        notification.message = "Take out trash";
        notification.from = "Ryan Carrell";
        notification.time = "Mar 9 6:40 PM";
        notification.title = "Apartment";
        your_array_list.add(notification);

        Notification notification1 = new Notification();
        notification1.message = "Get Eggs from Kroger";
        notification1.from = "Me";
        notification1.time = "Feb 22 4:00 PM";
        notification1.title = "Grocery";
        your_array_list.add(notification1);

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<Notification> arrayAdapter = new ArrayAdapter<Notification>(
                this,
                android.R.layout.simple_list_item_1,
                your_array_list );

        lv.setAdapter(arrayAdapter);
    }

//    public void onListItemClick(ListView parent, View v, int position, long id){
//
//        //Set background of all items to white
//        for (int i=0;i<parent.getChildCount();i++){
//            parent.getChildAt(i).setBackgroundColor(Color.WHITE);
//        }
//
//        v.setBackgroundColor(Color.CYAN);
//    }

    /**Called when the user clicks the Plus button */
    public void createReminder(View view) {
        Intent intent = new Intent(this, CreateNotificationActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
