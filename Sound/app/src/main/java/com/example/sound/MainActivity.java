package com.example.sound;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer player;
    AudioManager audioManager;

    public void play(View view)
    {
        player.start();

    }

    public void pause(View view)
    {
        player.pause();

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        int maxvol= audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentvolume= audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        player = MediaPlayer.create(this,R.raw.rainhigh);

        SeekBar volume=(SeekBar)findViewById(R.id.seekBar);
        volume.setMax(maxvol);
        volume.setProgress(currentvolume);

        volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("seekbar changed", Integer.toString(progress));
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        final SeekBar musicontrol = (SeekBar) findViewById(R.id.scrubSeekBar);

        musicontrol.setMax(player.getDuration());

        musicontrol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Scrub Seekbar changed",Integer.toString(progress));
                player.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run()
            {
                musicontrol.setProgress(player.getCurrentPosition());
            }
        },0,300);

    }
}
