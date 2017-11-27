package com.example.natalie.myhomework;


import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    String INFO = "INFO_";
    public static int toastCounter;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        toastCounter = 1;
        player = MediaPlayer.create(this, R.raw.hotel_california);
        player.start();
    }

    @Override
    public void onClick(View v) {
        if (toastCounter>8) {
            toastCounter = 1;
        }
        String id = "hotelCalifornia_"+Integer.toString(toastCounter);
        int resId = getResources().getIdentifier(id, "string", getPackageName());
        String s = getResources().getString(resId);

        Toast toast = Toast.makeText(
                getApplicationContext(),
                s, 	Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.BOTTOM, 0, 0);

        toast.show();
        toastCounter++;

    }


}



