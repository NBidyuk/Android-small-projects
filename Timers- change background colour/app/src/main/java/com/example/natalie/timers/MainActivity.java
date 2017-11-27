package com.example.natalie.timers;


import android.graphics.Color;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String INFO = "INFO_";
    boolean isRunning;
    Button button1;

    ConstraintLayout mConstraintLayout;
    int Red;
    int Green;
    int Blue;




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
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        //set = new ConstraintSet();
        mConstraintLayout = (ConstraintLayout) findViewById(R.id.activity_main);
        Red=0;
        Green=0;
        Blue=0;
        mConstraintLayout.setBackgroundColor(Color.rgb(Red, Green, Blue));


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
                    if (Red>=0 && Green==0 && Blue ==0)
                        Red++;
                    if (Red==255 && Green<255 && Blue ==0)
                        Green++;
                    if (Red>0 && Green==255 && Blue ==0)
                        Red--;
                    if (Red==0 && Green>0 && Blue ==0)
                        Green--;
                    int color = Color.rgb(Red, Green, Blue);
                    mConstraintLayout.setBackgroundColor(color);

                }
                h.postDelayed(this, 10);
            }
        });
    }

}
