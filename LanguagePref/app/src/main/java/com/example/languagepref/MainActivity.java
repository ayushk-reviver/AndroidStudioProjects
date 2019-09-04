package com.example.languagepref;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView textView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.english:
                setLaunguage("English");
                return true;
            case R.id.spanish:
                setLaunguage("Spanish");
                return true;
            default:
                return false;
        }

    }

    public void setLaunguage(String lang) {
        sharedPreferences = this.getSharedPreferences("com.example.languagepref", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("language", lang).apply();


        textView.setText(lang);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("com.example.languagepref", Context.MODE_PRIVATE);
        textView= findViewById(R.id.textView);

        //sharedPreferences.getString("language", "Error");

        //Log.i("The Lang",sharedPreferences.getString("language", "Error");)

        textView.setText(sharedPreferences.getString("language", "Error"));

        if (sharedPreferences.getString("language", "Error").equals("Error")) {



            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Choose Language")
                    .setMessage("Which Language you want to Choose!!!")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLaunguage("English");
                            Toast.makeText(MainActivity.this, "English Selected", Toast.LENGTH_SHORT).show();

                        }
                    }).setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    setLaunguage("Spanish");
                    Toast.makeText(MainActivity.this, "Spanish Selected", Toast.LENGTH_SHORT).show();

                }
            }).show();
        }
    }
}
