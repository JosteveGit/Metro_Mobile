package com.metro.metromobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.metro.metromobile.R;
import com.metro.metromobile.activities.LoginActivity;
import com.metro.metromobile.introduction.IntroductionActivity;
import com.metro.metromobile.utils.manager.SessionManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Toast.makeText(this, "Hey", Toast.LENGTH_SHORT).show();
        checkLoginStatus();
    }

    private void checkLoginStatus() {


    }
}


