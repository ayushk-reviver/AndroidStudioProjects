package com.example.multiactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SecondActivity extends AppCompatActivity
{

    public void goback(View view)
    {
        //Intent intent = new Intent(getApplicationContext(),MainActivity.class);

        //startActivity(intent);

        finish();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();


        Log.i("age", Integer.toString(intent.getIntExtra("age",0)));

    }
}
