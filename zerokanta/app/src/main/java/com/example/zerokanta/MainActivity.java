package com.example.zerokanta;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0 is orange 1 is pink and 2 is empty
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winner={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int active=0;
    boolean gameactive=true;
    Button playbutton=null;
    TextView text=null;

    public void dropIT(View view) {
        ImageView counter = (ImageView) view;

        Log.i("Tag", counter.getTag().toString());

        int userposition = Integer.parseInt(counter.getTag().toString());

        if (gamestate[userposition] == 2 && gameactive)
        {

            gamestate[userposition] = active;

            if (active == 0) {
                active = 1;
                counter.setImageResource(R.drawable.orange);

            } else {
                active = 0;
                counter.setImageResource(R.drawable.pink);
            }

            counter.setTranslationY(-1500);

            counter.animate().translationYBy(1500).setDuration(300);

            for (int[] winner : winner) {
                if (gamestate[winner[0]] == gamestate[winner[1]] && gamestate[winner[1]] == gamestate[winner[2]] && gamestate[winner[0]] != 2)
                {
                    playbutton=(Button) findViewById(R.id.button);
                    text=(TextView) findViewById(R.id.textView);



                    gameactive=false;
                    if (active == 0)
                    {
                        text.setText("Pink WON!!! the match");
                        //Toast.makeText(this, "Pink WON!!! the match", Toast.LENGTH_SHORT).show();
                    } else {
                        text.setText("Orange WON!!! the match");
                        //Toast.makeText(this, "Orange WON!!! the match", Toast.LENGTH_SHORT).show();
                    }

                    playbutton.setVisibility(View.VISIBLE);
                    text.setVisibility(View.VISIBLE);
                }

            }

        }
    }

    public void playAgain(View view)
    {
        Log.i("info","Play again pressed");
        playbutton=(Button) findViewById(R.id.button);
        text=(TextView) findViewById(R.id.textView);
        Log.i("info","Button and text view initialized");
        playbutton.setVisibility(View.INVISIBLE);
        text.setVisibility(View.INVISIBLE);
        Log.i("info","setting invisible");

        GridLayout lyout = (GridLayout) findViewById(R.id.gridLyot);
        Log.i("info","Grid Initialized");

        for(int i=0; i< lyout.getChildCount(); i++)
        {
            ImageView imgs=(ImageView)lyout.getChildAt(i);
            imgs.setImageDrawable(null);
        }

        for(int i=0; i<gamestate.length ; i++)
        {
            gamestate[i]=2;
        }

        //gamestate={2,2,2,2,2,2,2,2,2};
        //int[][] winner={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        active=0;
        gameactive=true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
