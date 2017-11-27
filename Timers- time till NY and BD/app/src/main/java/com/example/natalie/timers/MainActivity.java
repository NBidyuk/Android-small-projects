package com.example.natalie.timers;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String INFO = "INFO_";
    boolean isRunning;
    TextView NYtext;
    TextView BDtext;

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
        NYtext = (TextView) findViewById(R.id.textNY);
        BDtext = (TextView) findViewById(R.id.textBD);

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
                    SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy HH mm");//задаю формат даты
                    String newYear = "01 01 2018 00 00";//создаю строку по заданному формату
                    String birthday = "17 03 2018 00 00";
                    Date dateNY = null;
                    Date dateBD = null;
                    try {
                        dateNY = formatter.parse(newYear);
                        dateBD = formatter.parse(birthday);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    long dateInMilsNY = dateNY.getTime();
                    long dateInMilsBD = dateBD.getTime();
                    long diffInSecNY = (dateInMilsNY - now);
                    long diffInSecBD = (dateInMilsBD - now);
                    NYtext.setText(getTimeString(diffInSecNY));
                    BDtext.setText(getTimeString(diffInSecBD));

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
