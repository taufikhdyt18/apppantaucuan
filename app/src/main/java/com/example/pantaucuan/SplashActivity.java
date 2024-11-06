package com.example.pantaucuan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Delay for 3 seconds before navigating to LoginActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start LoginActivity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                // Close SplashActivity
                finish();
            }
        }, 3000); // 3000 milliseconds = 3 seconds
    }
}
