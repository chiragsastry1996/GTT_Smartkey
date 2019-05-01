package com.volvo.gtt_smartkey.SplashScreen;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;

import com.volvo.gtt_smartkey.R;
import com.volvo.gtt_smartkey.MainActivity.MainActivity;

public class SplashScreen extends WearableActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}