package com.example.timetables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar= (SeekBar) findViewById(R.id.seekBar);
        final ListView listView=(ListView) findViewById(R.id.listview);

        seekBar.setMax(21);
        seekBar.setMin(1);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            ArrayList<Integer> arrayList= new ArrayList<Integer>();



            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    arrayList.clear();
                Log.i("Seekbar value",String.valueOf(progress));

                for(int i=1; i<=10;i++)
                {
                    arrayList.add(i*seekBar.getProgress());
                }
                ArrayAdapter<Integer> arrayAdapter= new ArrayAdapter<Integer>(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
                listView.setAdapter(arrayAdapter);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
            }
        });




    }
}
