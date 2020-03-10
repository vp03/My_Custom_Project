package com.pentakotavishu.mycustomproject;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView time;
    Button start;
    TextView showdir;
    TextView score;
    Button north;
    Button south;
    Button east;
    Button west;
    int n;
    String dir;
    int correct=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MediaPlayer mediaPlayer= MediaPlayer.create(MainActivity.this,R.raw.christmas);

        time = findViewById(R.id.textView1);
        start = findViewById(R.id.clickButton);
        showdir = findViewById(R.id.textView3);
        score = findViewById(R.id.textView4);
        north = findViewById(R.id.north);
        south = findViewById(R.id.south);
        east = findViewById(R.id.east);
        west = findViewById(R.id.west);

        mediaPlayer.start();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Timer started!");

                dir = assign();
                showdir.setText(dir);



                new CountDownTimer(30000, 1000) {


                    public void onTick(long millisUntilFinished) {
                        time.setText("Seconds Remaining: " + millisUntilFinished / 1000);
                        north.setEnabled(true);
                        south.setEnabled(true);
                        east.setEnabled(true);
                        west.setEnabled(true);
                        score.setText("" + correct);
                    }

                    public void onFinish() {
                        time.setText("Done!");
                        north.setEnabled(false);
                        south.setEnabled(false);
                        east.setEnabled(false);
                        west.setEnabled(false);
                        correct=0;
                    }
                }.start();

            }
        });

        //method

    }

    public String assign() {

        Random rand = new Random();
        n = rand.nextInt(4);

        if (n == 0) {
            dir = "north";
            showdir.setText(dir);
        }

        if (n == 1) {
            dir = "east";
            showdir.setText(dir);
        }

        if (n == 2) {
            dir = "south";
            showdir.setText(dir);
        }

        if (n == 3) {
            dir = "west";
            showdir.setText(dir);
        }
        return dir;
    }

    public void clickedn(View view) {
        if(dir == "north") {
            correct=correct+1;
            score.setText("" + correct);
        }
        dir = assign();
        showdir.setText(dir);
    }

    public void clickede(View view) {
        if(dir == "east") {
            correct=correct+1;
            score.setText("" + correct);
        }
        dir = assign();
        showdir.setText(dir);
    }

    public void clickeds(View view) {
        if(dir == "south") {
            correct=correct+1;
            score.setText("" + correct);
        }
        dir = assign();
        showdir.setText(dir);
    }

    public void clickedw(View view) {
        if(dir == "west") {
            correct=correct+1;
            score.setText("" + correct);
        }
        dir = assign();
        showdir.setText(dir);
    }
}
