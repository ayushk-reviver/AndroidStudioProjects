package com.example.eggtimer;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button buttonstart, buttonstop;

    int seconds = 30;
    int minutes = 0;
    Timer timer;
    int lastseekerposition = 0;

    MediaPlayer mediaPlayer;
    SeekBar seekBar;


    private class seeker implements SeekBar.OnSeekBarChangeListener {

        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // Log the progress
            Log.d("DEBUG", "Progress is: " + progress);
            //set textView's text


            if (progress > lastseekerposition) {
                //right
                movetimeright();

                lastseekerposition = seekBar.getProgress();

                textView.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));


            } else {
                //left
                movetimeleft();

                lastseekerposition = seekBar.getProgress();

                textView.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));


            }

            if (seekBar.getProgress() == 0) {
                minutes = 0;
                seconds = 30;
                textView.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));

            }


        }


        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        public void onStopTrackingTouch(SeekBar seekBar) {

        }

    }

    public void movetimeright() {
        if (seconds != 30) {
            seconds = seconds + 30;
        } else {
            minutes += 1;
            seconds = 0;
        }


    }

    public void movetimeleft() {
        if (seconds != 0) {
            seconds = seconds - 30;

        } else if (minutes != 0) {
            minutes -= 1;
            seconds = 30;
        }


    }


    private class buttonstartpress implements View.OnClickListener {


        @Override
        public void onClick(View v) {


            buttonstart.setVisibility(View.INVISIBLE);
            buttonstop.setVisibility(View.VISIBLE);
            seekBar.setEnabled(false);


            timer.scheduleAtFixedRate(new TimerTask()
            {
                Handler handler = new Handler();

                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));
                        }
                    });


                    if (seconds == 0 && minutes != 0) {
                        seconds = 60;
                        minutes -= 1;
                        seconds = seconds - 1;
                    } else if (seconds == 0 && minutes == 0) {
                        //timer.cancel();
                        mediaPlayer.start();


                        handler.postDelayed(new Runnable() {
                            public void run() {

                                autostop();

                            }
                        }, 3000);

                    } else if (minutes != 0 && seconds != 0) {
                        seconds = seconds - 1;
                    } else if (minutes == 0 && seconds != 0) {
                        seconds = seconds - 1;
                    }


                }
            }, 0, 1000);


        }
    }

    public void autostop() {
        buttonstop.setVisibility(View.INVISIBLE);
        buttonstart.setVisibility(View.VISIBLE);

        seekBar.setProgress(0);
        lastseekerposition = -1;
        seekBar.setEnabled(true);
        //if (timer != null)
        timer.cancel();

        seconds = 30;
        textView.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));

        timer = new Timer();
    }


    private class buttonstoppress implements View.OnClickListener {

        @Override
        public void onClick(View v) {


            autostop();


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = new Timer();

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(10);
        lastseekerposition = seekBar.getProgress();
        seekBar.setOnSeekBarChangeListener(new seeker());

        textView = (TextView) findViewById(R.id.textView);
        textView.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));

        buttonstart = (Button) findViewById(R.id.buttonStart);
        buttonstart.setOnClickListener(new buttonstartpress());

        buttonstop = (Button) findViewById(R.id.buttonStop);
        buttonstop.setOnClickListener(new buttonstoppress());

        mediaPlayer = MediaPlayer.create(this, R.raw.buzzer);

    }
}
