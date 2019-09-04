package com.example.databse;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users",MODE_PRIVATE,null);

        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS usr (name VARCHAR, age int(3))");

        myDatabase.execSQL("INSERT INTO usr (name, age) VALUES ('ayush', 25)");
        myDatabase.execSQL("INSERT INTO usr (name, age) VALUES ('kriti', 28)");
        myDatabase.execSQL("INSERT INTO usr (name, age) VALUES ('patrik', 19)");
        myDatabase.execSQL("INSERT INTO usr (name, age) VALUES ('tanya', 21)");

        Cursor cursor = myDatabase.rawQuery("SELECT * FROM usr",null);

        int nameDex = cursor.getColumnIndex("name");
        int ageDex = cursor.getColumnIndex("age");

        cursor.moveToFirst();


        while (cursor != null)
        {
            Log.i("name",cursor.getString(nameDex));
            Log.i("age",cursor.getString(ageDex));

            cursor.moveToNext();
        }


    }
}
