package com.example.natalie.myhomework;

        import android.graphics.Color;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import java.util.Random;


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
        int num = generateNumber(1,40);


        button1.setTextSize(num);
        button1.setTextColor(generateColour());
        button1.setBackgroundColor(generateColour());
    }


    public int generateNumber(int min, int max)
    {
        Random rand = new Random();
        int n= rand.nextInt(max) + min;
        return n;
    }

    public int generateColour()
    {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        return color;
    }
}



