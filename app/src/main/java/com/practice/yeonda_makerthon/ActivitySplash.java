package com.practice.yeonda_makerthon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.practice.yeonda_makerthon.login.ActivityLogin;

public class ActivitySplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler hd = new Handler();
        hd.postDelayed(new splashHandler(), 1000);
    }

    private class splashHandler implements Runnable {
        @Override
        public void run() {
            startActivity(new Intent(getApplicationContext(), ActivityLogin.class));
            ActivitySplash.this.finish();
        }
    }

    @Override
    public void onBackPressed() {

    }
}