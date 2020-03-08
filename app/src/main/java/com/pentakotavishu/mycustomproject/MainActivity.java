package com.pentakotavishu.mycustomproject;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView time;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MediaPlayer mediaPlayer= MediaPlayer.create(MainActivity.this,R.raw.christmas);

        time = findViewById(R.id.textView1);
        start = findViewById(R.id.clickButton);

        mediaPlayer.start();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Timer started!");

                new CountDownTimer(30000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        time.setText("Seconds Remaining: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        time.setText("done!");
                    }
                }.start();

            }
        });
    }
}
