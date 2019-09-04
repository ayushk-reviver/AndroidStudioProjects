package com.example.showhide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public void show(View view)
    {
        TextView textView= (TextView) findViewById(R.id.textView);
        textView.setVisibility(View.VISIBLE);

    }

    public void hide(View view)
    {
        TextView textView= (TextView) findViewById(R.id.textView);
        textView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
