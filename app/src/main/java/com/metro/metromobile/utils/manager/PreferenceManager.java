package com.metro.metromobile.utils.manager;

import android.content.Context;
import android.content.SharedPreferences;

import static com.metro.metromobile.utils.keys.PreferenceKeys.PREFERENCE_KEY_APP_NAME;
import static com.metro.metromobile.utils.keys.PreferenceKeys.PREFERENCE_KEY_IS_DARK_MODE_APPEARANCE;
import static com.metro.metromobile.utils.keys.PreferenceKeys.PREFERENCE_KEY_USER_CURRENT_THEME;


public class PreferenceManager {

    private static Context mAppContext;

    // Prevent instantiation
    private PreferenceManager() { }

    public static void init(Context appContext) {
        mAppContext = appContext;
    }

    private static SharedPreferences getSharedPreferences() {
        return mAppContext.getSharedPreferences(PREFERENCE_KEY_APP_NAME, Context.MODE_PRIVATE);
    }

    // DARK MODE PREFERENCE-------------------------------------------------------------------------
    public static boolean isDarkMode() {
        return getSharedPreferences().getBoolean(PREFERENCE_KEY_IS_DARK_MODE_APPEARANCE, true);
    }

    public static void setDarkMode(Boolean value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(PREFERENCE_KEY_IS_DARK_MODE_APPEARANCE, value);
        editor.apply();
    }


}