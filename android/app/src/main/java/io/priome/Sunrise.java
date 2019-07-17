package io.priome;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.VideoView;


public class Sunrise extends AppCompatActivity {

    private MediaPlayer audioPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Wake the phone
        WakeLocker.acquire(this);

        // Make fullscreen, landscape, etc.
        setScreenOptions();

        // Set the view
        setContentView(R.layout.sunrise);

        // Play audio: birdsong
        audioPlayer = MediaPlayer.create(this, R.raw.rainforest_1a);
        audioPlayer.start();

        // Play video: sunrise
        VideoView videoPlayer = findViewById(R.id.videoView);
        Uri videoURI = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video_1a);
        videoPlayer.setVideoURI(videoURI);
        videoPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        videoPlayer.start();


        // Dismiss button
        Button dismissBtn = findViewById(R.id.dismissBtn);
        dismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioPlayer.release();
                finish();
            }
        });
    }

    private void setScreenOptions() {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON );
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN );
    }

    @Override
    public void onStop () {
        super.onStop();
        audioPlayer.release();
    }
}
