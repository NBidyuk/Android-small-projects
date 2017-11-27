package com.example.natalie.timers;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String INFO = "INFO_";
    Button b;
    int delay;
    int count;


    Camera camera;
    boolean isFlashOn = false;
    boolean hasFlash;
    Parameters params;
    boolean running;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isFlashOn", isFlashOn);
        outState.putBoolean("hasFlash", hasFlash);
        Log.i(INFO, "onSaveState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
        hasFlash = savedState.getBoolean("hasFlash");
        isFlashOn = !savedState.getBoolean("isFlashOn");
        Log.i(INFO, "onRestoreState");
        onClick(null);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(INFO, "onCreate");

        b = (Button) findViewById(R.id.flash);
        b.setOnClickListener(this);

        hasFlash = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!hasFlash) {
            getSupportActionBar().setTitle("You have no camera!");
        } else {
            getCamera();
        }
    }

    @Override
    public void onClick(View view) {
        if (!running) {
            getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#000000\">LIGHT ON!</font>")));
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            b.setText("Выключить!");
            runTimer();
            running=true;

        } else {
            getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#FFFFFF\">LIGHT OFF...</font>")));
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
            b.setText("Включить!");
            turnOffFlash();
            running=false;

        }
    }



    public void runTimer() {

        final Handler h = new Handler();
        h.post(new Runnable() {
            @Override
            public void run() {
                if(running) {
                    if (isFlashOn) {
                        turnOffFlash();
                    } else {
                        turnOnFlash();
                    }

                    h.postDelayed(this, 500);
                }

            }
        });

    }

    private void getCamera() {
        if (camera == null) {
            try {
                camera = Camera.open();
            } catch (RuntimeException e) {
                getSupportActionBar().setTitle("Error in getCamera() method");
            }
        }
    }

    private void turnOnFlash() {
        if (!isFlashOn) {
            if (camera == null || !hasFlash) {
                return;
            }

            params = camera.getParameters();
            params.setFlashMode(Parameters.FLASH_MODE_TORCH);
            camera.setParameters(params);
            camera.startPreview();
            isFlashOn = true;
        }
    }

    private void turnOffFlash() {
        if (isFlashOn) {
            if (camera == null) {
                return;
            }

            params = camera.getParameters();
            params.setFlashMode(Parameters.FLASH_MODE_OFF);
            camera.setParameters(params);
            camera.stopPreview();
            isFlashOn = false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(INFO, "onPause");
        turnOffFlash();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(INFO, "onResume");
        if (isFlashOn) {
            isFlashOn = false;
            onClick(null);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(INFO, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(INFO, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(INFO, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(INFO, "onDestroy");
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }
}

