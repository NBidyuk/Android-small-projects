package com.example.natalie.myhomework;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;




public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button button1 = (Button) findViewById(R.id.button1);
        int w = button1.getWidth();
        int h = button1.getHeight();
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;

        if( w==screenWidth) {
            button1.setWidth(126);
            button1.setHeight(76);
        }
        else {
            button1.setWidth(w + 10);
            button1.setHeight(h + 10);
        }

    }
}



