package com.example.login_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {

    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

         timer = new Timer();
         timer.schedule(new TimerTask() {
             @Override
             public void run() {
                 Intent intent=new Intent(SplashScreenActivity.this, RegisterScreenActivity.class);
                 startActivity(intent);
                 finish();
             }
         },3500);
    }
}