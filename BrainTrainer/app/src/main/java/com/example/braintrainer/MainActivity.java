package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton, playagain, btn1, btn2, btn3, btn4;
    TextView sumTextview, timertextview, scoretextview, resulttextview;
    int a, b, correct, incorrect;
    boolean Gameprogress = false, timerexpire = false;
    GridLayout gridLayout;
    int gametimervalue = 30;
    ArrayList<Integer> buttonrandval;

    public void start(View view) {

        Gameprogress = true;
        correct = 0;
        incorrect = 0;

        gameboard();
        buttonsetvalue();
        //Log.i("Arraylist size", String.valueOf(buttonrandval.size()));
        displayuchangedvalues();
        gametimer();

        //scoretextview.setText(correct+"//" + incorrect);


    }

    public void playAgain(View view) {

        playagain.setVisibility(View.INVISIBLE);
        timertextview.setText("30s");
        timerexpire = false;
        Gameprogress = true;
        correct = 0;
        incorrect = 0;
        resulttextview.setText("let's PLAY Again");

        gameboard();
        buttonsetvalue();
        //Log.i("Arraylist size", String.valueOf(buttonrandval.size()));
        displayuchangedvalues();
        gametimer();

    }

    public void chooseAnswer(View view) {


        if (!timerexpire) {
            Log.i("into", view.getTag().toString());

            switch (view.getTag().toString()) {
                case "0":
                    checkcorrectanswer(Integer.parseInt(btn1.getText().toString()));
                    buttonsetvalue();
                    displayuchangedvalues();

                    break;
                case "1":checkcorrectanswer(Integer.parseInt(btn2.getText().toString()));
                    buttonsetvalue();
                    displayuchangedvalues();
                    break;
                case "2":checkcorrectanswer(Integer.parseInt(btn3.getText().toString()));
                    buttonsetvalue();
                    displayuchangedvalues();
                    break;
                case "3":checkcorrectanswer(Integer.parseInt(btn4.getText().toString()));
                    buttonsetvalue();
                    displayuchangedvalues();
                    break;

            }




        }


    }

    public void checkcorrectanswer(int e) {
        if (e == a + b) {
            correct++;
            resulttextview.setText("CORRECT ;-)");
        } else {
            incorrect++;
            resulttextview.setText("INCORRECT :-(");
        }
        scoretextview.setText(correct + "/" + incorrect);

    }

    public void displayuchangedvalues()
    {
        sumTextview.setText(a + "+" + b);
        Log.i("info", "94");
        btn1.setText(Integer.toString(buttonrandval.get(0)));
        btn2.setText(Integer.toString(buttonrandval.get(1)));
        btn3.setText(Integer.toString(buttonrandval.get(2)));
        btn4.setText(Integer.toString(buttonrandval.get(3)));
        Log.i("info", "99");
        scoretextview.setText(correct + "/" + incorrect);

    }

    public void buttonsetvalue()
    {
        a = makeRandomNumber(21);
        b = makeRandomNumber(21);

        int c = makeRandomNumber(4);

        if(buttonrandval.size() > 0)
            buttonrandval.clear();

        for (int i = 0; i < 4; i++)
        {
            Log.i("Arraylist loop", String.valueOf(i));
            if (c == i) {
                buttonrandval.add(a + b);
            }
            else
                {
                    int d = makeRandomNumber(41);

                while (d == a + b) {
                    d = makeRandomNumber(41);

                }

                buttonrandval.add(d);
            }
        }

        Log.i("Arraylist size", String.valueOf(buttonrandval.size()));


    }

    public void gameboard() {

        if (Gameprogress) {
            goButton.setVisibility(View.INVISIBLE);
            playagain.setVisibility(View.INVISIBLE);
            sumTextview.setVisibility(View.VISIBLE);
            timertextview.setVisibility(View.VISIBLE);
            scoretextview.setVisibility(View.VISIBLE);
            resulttextview.setVisibility(View.VISIBLE);
            gridLayout.setVisibility(View.VISIBLE);


        } else {
            //goButton.setVisibility(View.INVISIBLE);
            playagain.setVisibility(View.INVISIBLE);
            sumTextview.setVisibility(View.INVISIBLE);
            timertextview.setVisibility(View.INVISIBLE);
            resulttextview.setVisibility(View.INVISIBLE);
            scoretextview.setVisibility(View.INVISIBLE);
            gridLayout.setVisibility(View.INVISIBLE);

        }

    }

    public int makeRandomNumber(int bound) {
        Random random = new Random();

        return random.nextInt(bound);

    }

    public void gametimer() {
        new CountDownTimer(gametimervalue * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                --gametimervalue;
                timertextview.setText(String.valueOf(gametimervalue) + "s");
            }

            public void onFinish() {
                playagain.setVisibility(View.VISIBLE);
                Gameprogress = false;
                gametimervalue = 30;
                timerexpire = true;

            }

        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = (Button) findViewById(R.id.button);
        playagain = (Button) findViewById(R.id.playAgainButton);
        btn1 = (Button) findViewById(R.id.button0);
        btn2 = (Button) findViewById(R.id.button1);
        btn3 = (Button) findViewById(R.id.button2);
        btn4 = (Button) findViewById(R.id.button3);
        sumTextview = (TextView) findViewById(R.id.sumTextView);
        timertextview = (TextView) findViewById(R.id.timerTextView);
        scoretextview = (TextView) findViewById(R.id.scoreTextView);
        resulttextview = (TextView) findViewById(R.id.resultTextView);
        gridLayout = (GridLayout) findViewById(R.id.gridLayaout);

        buttonrandval = new ArrayList<Integer>();

        gameboard();
    }


}
