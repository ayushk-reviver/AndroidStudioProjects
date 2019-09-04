package com.example.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.sharedpreferences", Context.MODE_PRIVATE);

        /*ArrayList<String> friends = new  ArrayList<String>();
        friends.add("nich");
        friends.add("minnion");
        friends.add("gaurav");
        friends.add("addy");

        try {

            sharedPreferences.edit().putString("friends",ObjectSerializer.serialize(friends)).apply();
            Log.i("Info", ObjectSerializer.serialize(friends));

        } catch (Exception e) {
            e.printStackTrace();
        }*/


        ArrayList<String> newfriends = new ArrayList<String>();

        try {
            newfriends = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends",ObjectSerializer.serialize(new ArrayList<String>())));

        } catch (Exception e) {
            e.printStackTrace();
        }


        //sharedPreferences.edit().putString("userName","Ayush").apply();

        //String username = sharedPreferences.getString("userName","noname");

        Log.i("Info", String.valueOf(newfriends));

        //sharedPreferences.edit().putString("userName","Ayush").apply();

    }
}
