package com.metro.metromobile.app;

import android.app.Application;
import com.metro.metromobile.R;
import com.metro.metromobile.utils.manager.PreferenceManager;
import com.metro.metromobile.utils.manager.RefreshManager;
import com.metro.metromobile.utils.manager.SessionManager;
import com.metro.metromobile.utils.manager.TokenManager;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class MetroApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initAppManagers();
        setAppFont();
    }

    private void setAppFont() {
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/arial.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
    }

    public void initAppManagers() {
        SessionManager.init(getApplicationContext());
        RefreshManager.init(getApplicationContext());
        PreferenceManager.init(getApplicationContext());
        TokenManager.init(getApplicationContext());
    }
}
