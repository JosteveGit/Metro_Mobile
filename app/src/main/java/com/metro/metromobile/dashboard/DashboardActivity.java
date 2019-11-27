package com.metro.metromobile.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.metro.metromobile.R;
import com.metro.metromobile.activities.LoginActivity;
import com.metro.metromobile.activities.RegisterActivity;
import com.metro.metromobile.introduction.IntroductionActivity;
import com.metro.metromobile.shared.base.BaseActivity;
import com.metro.metromobile.utils.manager.SessionManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class DashboardActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        checkLoginStatus();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard,R.id.navigation_history, R.id.navigation_notifications)
                .build();


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("Welcome "+ SessionManager.getFirstName());


    }

    private void checkLoginStatus() {
        if (!SessionManager.isUserLogin()) {
            if (SessionManager.isSeenLandingScreen()) {
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
            } else {
                startActivity(new Intent(this, IntroductionActivity.class));
                finish();
            }
        }
    }
}
