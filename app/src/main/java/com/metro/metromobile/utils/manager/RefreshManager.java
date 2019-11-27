package com.metro.metromobile.utils.manager;

import android.content.Context;
import android.content.SharedPreferences;

import static com.metro.metromobile.utils.keys.PreferenceKeys.PREFERENCE_KEY_APP_NAME;
import static com.metro.metromobile.utils.keys.PreferenceKeys.PREFERENCE_KEY_IS_REFRESH_APP;


public class RefreshManager {

    private static Context mAppContext;

    // Prevent instantiation
    private RefreshManager() { }

    public static void init(Context appContext) {
        mAppContext = appContext;
    }

    private static SharedPreferences getSharedPreferences() {
        return mAppContext.getSharedPreferences(PREFERENCE_KEY_APP_NAME, Context.MODE_PRIVATE);
    }

    public static boolean isRefreshApp() {
        return getSharedPreferences().getBoolean(PREFERENCE_KEY_IS_REFRESH_APP, false);
    }

    public static void setRefreshApp(Boolean value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(PREFERENCE_KEY_IS_REFRESH_APP, value);
        editor.apply();
    }
}
