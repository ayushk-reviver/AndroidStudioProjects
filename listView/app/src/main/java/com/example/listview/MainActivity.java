package com.example.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //String[] family= {"ayush","ayush2", "ayush3", "ayush4"};

        final ArrayList<String> family= new ArrayList<String>();
        family.add("ayush3");
        family.add("ayush4");
        family.add("ayush5");
        family.add("ayush6");
        family.add("ayush7");

        final ArrayList<String> friends = new ArrayList<String>(asList("gaurav","mark","Jane", "Susan"));

        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, family);

        ListView mylistview= (ListView) findViewById(R.id.myListView);

        mylistview.setAdapter(arrayAdapter);

        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i("Person selected", family.get(position));

                Toast.makeText(getApplicationContext(), family.get(position),Toast.LENGTH_SHORT).show();

            }
        });

    }
}
