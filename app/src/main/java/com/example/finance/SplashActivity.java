package com.example.finance;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 2000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Log lifecycle event
        android.util.Log.d("LifecycleEvents", "SplashActivity: onCreate called");

        // Delayed navigation to DashboardActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish(); // Close the splash activity
            }
        }, SPLASH_DURATION);
    }

    @Override
    protected void onStart() {
        super.onStart();
        android.util.Log.d("LifecycleEvents", "SplashActivity: onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        android.util.Log.d("LifecycleEvents", "SplashActivity: onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        android.util.Log.d("LifecycleEvents", "SplashActivity: onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        android.util.Log.d("LifecycleEvents", "SplashActivity: onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.util.Log.d("LifecycleEvents", "SplashActivity: onDestroy called");
    }
}