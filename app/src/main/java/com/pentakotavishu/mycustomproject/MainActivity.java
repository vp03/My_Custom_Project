package com.pentakotavishu.mycustomproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText nameText;
    String name;
    TextView time;
    Button start;
    TextView showdir;
    TextView score;
    TextView high_score;
    Button north;
    Button south;
    Button east;
    Button west;
    int n;
    String dir;
    int correct=0;

    //note to self: make a field where people can write their name (between time and north)and then make a
    // class in MainActivity that has their name and their score and then can use GSON

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MediaPlayer mediaPlayer= MediaPlayer.create(MainActivity.this,R.raw.christmas);



        nameText = findViewById(R.id.editText1);
        time = findViewById(R.id.textView1);
        start = findViewById(R.id.clickButton);
        showdir = findViewById(R.id.textView3);
        score = findViewById(R.id.textView5);
        high_score = findViewById(R.id.textView7);
        north = findViewById(R.id.north);
        south = findViewById(R.id.south);
        east = findViewById(R.id.east);
        west = findViewById(R.id.west);

        name = nameText.getText().toString();


        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("high", 0);
        editor.apply();

        final Gson gson = new Gson();

        final Person person = new Person();


        mediaPlayer.start();


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Timer started!");

                dir = assign();
                showdir.setText(dir);



                new CountDownTimer(5000, 1000) {


                    public void onTick(long millisUntilFinished) {
                        time.setText("Seconds Remaining: " + millisUntilFinished / 1000);
                        north.setEnabled(true);
                        south.setEnabled(true);
                        east.setEnabled(true);
                        west.setEnabled(true);
                        score.setText(" " + correct);
                    }

                    public void onFinish() {
                        time.setText("Done!");
                        north.setEnabled(false);
                        south.setEnabled(false);
                        east.setEnabled(false);
                        west.setEnabled(false);
                        if(correct > sharedPreferences.getInt("high", 0)){
                            editor.putInt("high", correct);
                            editor.apply();
                            Log.i("correct", "" + correct);
                            Log.i("high_val", "" + sharedPreferences.getInt("high", 0));
                            high_score.setText(" " + sharedPreferences.getInt("high", 0));
                        }
                        name = nameText.getText().toString();
                        person.setName(name);
                        person.setScore(correct);
                        String json = gson.toJson(person);
                        Log.i("person to json", "" + json);
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
            score.setText(" " + correct);
        }
        dir = assign();
        showdir.setText(dir);
    }

    public void clickede(View view) {
        if(dir == "east") {
            correct=correct+1;
            score.setText(" " + correct);
        }
        dir = assign();
        showdir.setText(dir);
    }

    public void clickeds(View view) {
        if(dir == "south") {
            correct=correct+1;
            score.setText(" " + correct);
        }
        dir = assign();
        showdir.setText(dir);
    }

    public void clickedw(View view) {
        if(dir == "west") {
            correct=correct+1;
            score.setText(" " + correct);
        }
        dir = assign();
        showdir.setText(dir);
    }



    public class Person{

        String name;
        int score;

        public Person(){

        }

        public Person(String s, int score_) {
            name = s;
            score = score_;
        }

        public String getName(){
            return name;

        }

        public int getScore(){
            return score;

        }

        public void setName(String s){
            name = s;

        }

        public void setScore(int score_){
            score = score_;
        }

    }

}
