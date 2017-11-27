package com.example.natalie.myhomework;

        import android.graphics.Color;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static int clicks =0;
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

        if (clicks>=20){
            button1.setActivated(false);
            button1.setBackgroundColor(Color.parseColor("grey"));
            button1.setText("Sorry. The button is broken!");
        }

        else {
            clicks++;
            button1.setText(Integer.toString(clicks));
        }
    }
}



