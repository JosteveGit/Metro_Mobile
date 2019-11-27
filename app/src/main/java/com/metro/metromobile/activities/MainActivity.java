package com.metro.metromobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.metro.metromobile.R;
import com.metro.metromobile.dashboard.DashboardActivity;
import com.metro.metromobile.introduction.IntroductionActivity;
import com.metro.metromobile.utils.manager.SessionManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkLoginStatus();
    }

    private void checkLoginStatus() {

            if (SessionManager.isSeenLandingScreen()) {
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }else {
                startActivity(new Intent(this, IntroductionActivity.class));
                finish();
            }
        }
    }
