package com.example.natalie.timers;


import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String INFO = "INFO_";
    boolean isRunning;
    public static long startTime;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("run", isRunning);
        Log.i(INFO, "onSaveState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
        isRunning = savedState.getBoolean("run");
        Log.i(INFO, "onRestoreState");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        startTime = System.currentTimeMillis();
    }

    @Override
    public void onClick(View v) {

        if (!isRunning) {
            isRunning = true;
            runTimer();
        } else {
            isRunning = false;
        }
    }

    public void runTimer() {

        final Handler h = new Handler();

        h.post(new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    long now = System.currentTimeMillis();
                    long diffInSec = (now - startTime);
                    getSupportActionBar().setTitle(getTimeString(diffInSec));
                }
                h.postDelayed(this, 1);
            }
        });

    }

    public String getTimeString(long sec) {
        sec /= 1000;
        int seconds = (int) (sec % 60);
        sec /= 60;
        int minutes = (int) (sec % 60);
        sec /= 60;
        int hours = (int) (sec % 24);
        sec /= 24;
        int days = (int) sec;
        String timeLeft = Integer.toString(days) + "d "
                + Integer.toString(hours) + "h "
                + Integer.toString(minutes) + "m "
                + Integer.toString(seconds) + "s ";
        return timeLeft;
    }
}

