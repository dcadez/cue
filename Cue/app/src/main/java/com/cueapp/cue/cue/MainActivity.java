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
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.facebook.FacebookSdk;
import com.firebase.client.FirebaseError;

import android.content.Intent;

import org.json.JSONArray;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        AccessToken accessToken = AccessToken.getCurrentAccessToken();

        if(accessToken != null){
            Log.e("LOGIN CHECK","THIS USER IS LOGGED IN");
            onFacebookAccessTokenChange(accessToken);

            /* make the API call */
            GraphRequest.newMyFriendsRequest(accessToken, new GraphRequest.GraphJSONArrayCallback() {
                @Override
                public void onCompleted(JSONArray json, GraphResponse response) {
                    //mUserFriends.clear();
                    Log.e("WTF", Integer.toString(json.length()));
                    //Log.e("WTF", AccessToken.getCurrentAccessToken().toString());

                    for (int i = 0; i < json.length(); i++) {
                        try {
                            String friend = json.getJSONObject(i).getString("name");
                            String friend_id = json.getJSONObject(i).getString("id");
                            Log.e("Proximity", "Adding friend: " + friend + ", id: " + friend_id);
//                            mUserFriends.add(new Friend(friend, Long.valueOf(friend_id)));
                        } catch (Exception e) {
                            Log.e("Proximity", "Exception adding friend: " + e.toString());
                        }
                    }
                }
            }).executeAsync();


        } else{
            Log.e("LOGIN CHECK","NOT LOGGED IN BRO");
            Intent intent = new Intent(this, FacebookLoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        FacebookSdk.sdkInitialize(getApplicationContext());

        toolbar.setTitle("Cue");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    }

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

    private void onFacebookAccessTokenChange(AccessToken token) {
        Firebase ref = new Firebase("https://cue-app.firebaseio.com");
        if (token != null) {
            ref.authWithOAuthToken("facebook", token.getToken(), new Firebase.AuthResultHandler() {
                @Override
                public void onAuthenticated(AuthData authData) {
                    // The Facebook user is now authenticated with your Firebase app
                    Log.d("firebase", "Logged into firebase");
                }
                @Override
                public void onAuthenticationError(FirebaseError firebaseError) {
                    // there was an error
                    Log.d("firebase", "firebase error");
                }
            });
        } else {
        /* Logged out of Facebook so do a logout from the Firebase app */
            Log.d("firebase", "Logged out of firebase");
            ref.unauth();
        }
    }
}
