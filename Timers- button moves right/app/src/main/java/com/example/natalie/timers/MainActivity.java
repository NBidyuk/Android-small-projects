package com.example.natalie.timers;


import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String INFO = "INFO_";
    boolean isRunning;
    Button button1;
    public  static ConstraintSet set;
    ConstraintLayout mConstraintLayout;
    public float bias;

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
        set = new ConstraintSet();
        mConstraintLayout = (ConstraintLayout) findViewById(R.id.activity_main);

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
                    if(bias>=1){
                        return;
                    }
                    set.clone(mConstraintLayout);
                    bias+=0.01f;
                    set.setHorizontalBias(button1.getId(), bias);
                    set.applyTo(mConstraintLayout);
                }
                h.postDelayed(this, 100);
            }
        });
    }

}
