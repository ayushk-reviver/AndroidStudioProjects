package com.example.vdo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView vdo=(VideoView) findViewById(R.id.videoView);

        vdo.setVideoPath("android.resource://"+getPackageName() + "/" + R.raw.demovideo);
        MediaController cntrl= new MediaController(this);
        cntrl.setAnchorView(vdo);
        vdo.setMediaController(cntrl);

        vdo.start();

    }
}
