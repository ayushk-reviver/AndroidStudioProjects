package com.example.exampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//    public void clickFunction(View view)
//    {
//
//        EditText nameEditText = (EditText) findViewById(R.id.nameEditText2);
//        EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);
//        Log.i("info", "Button pressed!");
//        Log.i("Name",nameEditText.getText().toString());
//        Log.i("Password",passwordEditText.getText().toString());
//
//        Toast.makeText(this,"Hello "+ nameEditText.getText().toString(),Toast.LENGTH_LONG).show();
//    }

    public void switchCat(View view)
    {
        Log.i("Info","change pressed");
        ImageView img=(ImageView) findViewById(R.id.catImageView);
        img.setImageResource(R.drawable.cat2);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
