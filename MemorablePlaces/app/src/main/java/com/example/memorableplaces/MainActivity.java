package com.example.memorableplaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    static ArrayList<String> mapList;
    static ArrayList<LatLng> locations;
    static ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.memorableplaces", Context.MODE_PRIVATE);
        ArrayList<String> lat = new ArrayList<>();
        ArrayList<String> longgitude = new ArrayList<>();

        mapList= new ArrayList<String>();

        locations = new ArrayList<LatLng>();

        mapList.clear();
        locations.clear();
        lat.clear();
        longgitude.clear();

        try{

            mapList = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("maplist",ObjectSerializer.serialize(new ArrayList<String>())));
            lat = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("lats",ObjectSerializer.serialize(new ArrayList<String>())));
            longgitude = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("longs",ObjectSerializer.serialize(new ArrayList<String>())));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if(mapList.size()>0 && lat.size() >0 && longgitude.size() >0)
        {
            if(mapList.size() == lat.size() && mapList.size() == longgitude.size())
            {
                for(int i = 0 ; i<lat.size(); i++)
                {
                    locations.add(new LatLng(Double.parseDouble(lat.get(i)),Double.parseDouble(longgitude.get(i))));
                }

            }
        }else
        {
            mapList.add("Add new Place...");
            locations.add(new LatLng(0,0));
        }

        ListView listView= (ListView) findViewById(R.id.mapListView);




        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,mapList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                //Toast.makeText(MainActivity.this,Integer.toString(position),Toast.LENGTH_SHORT).show();

                Intent mapInt= new Intent(getApplicationContext(), MapsActivity.class);
                mapInt.putExtra("placeNumber",position);

                startActivity(mapInt);

            }
        });


    }
}
