package com.example.noteapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class noteEditor extends AppCompatActivity
{
    EditText editText;
    int noteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        editText = (EditText)findViewById(R.id.editText);
        Intent intent = getIntent();
        noteID = intent.getIntExtra("noteID",-1);

        if(noteID != -1)
        {
            editText.setText(MainActivity.notestitle.get(noteID));
        }
        else
        {
            MainActivity.notestitle.add("");
            noteID = MainActivity.notestitle.size()-1;
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                MainActivity.notestitle.set(noteID,String.valueOf(s));
                MainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.noteapp", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(MainActivity.notestitle);
                sharedPreferences.edit().putStringSet("notes",set).apply();
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

    }
}
