package com.example.aiaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread thread = new Thread() {
            public void run(){
                try {
                    sleep(3000);
                }catch (InterruptedException e){
                }finally {
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
                    finish();
                }
            }
        };
        thread.start();
    }
}