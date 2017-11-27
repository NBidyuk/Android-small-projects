package com.example.natalie.timers;



        import android.graphics.Color;
        import android.os.Handler;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static int clicks;
    public  boolean isRunning;
    Button button1;
    long startTime;
    TextView text;
    Button press;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        text = (TextView)findViewById(R.id.text);
        press = (Button) findViewById(R.id.buttonPress);
        press.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonPress:
                if (isRunning) {
                    clicks++;
                    text.setText(clicks+"");

                }
                break;


            case R.id.button1:
                if (!isRunning) {
                    startTime = System.currentTimeMillis();
                    clicks = 0;
                    isRunning = true;
                    runTimer();
                    button1.setText("");
                    break;
                }

        }
    }

    public void runTimer() {

        final Handler h = new Handler();
        h.post(new Runnable() {
            @Override
            public void run() {
                if (isRunning) {

                    long time = System.currentTimeMillis();
                    long timeDiff = time-startTime;
                    getSupportActionBar().setTitle(timeDiff/1000+"");


                    if((int)(timeDiff/1000)>=20){
                        isRunning=false;
                        Toast.makeText(MainActivity.this, "You pressed "+ clicks+ " times", Toast.LENGTH_SHORT).show();
                        button1.setText("Start");
                    }


                }
                h.postDelayed(this, 10);
            }
        });
    }

}
