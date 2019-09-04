package com.example.vdos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    boolean bart=true;

    public void fadeStart(View view){
        Log.i("info","image view pressed");

        ImageView img=(ImageView) findViewById(R.id.imageView);
        ImageView img2=(ImageView) findViewById(R.id.homerImageView);


        if(bart)
        {
            bart=false;
            img.animate().alpha(0).setDuration(2000);
            img2.animate().alpha(1).setDuration(2000);
        }else
        {
            bart=true;
            img.animate().alpha(1).setDuration(2000);
            img2.animate().alpha(0).setDuration(2000);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img=(ImageView) findViewById(R.id.imageView);

        img.setX(-1000);
        img.animate().translationXBy(1000).rotation(3600).setDuration(2000);
    }
}
