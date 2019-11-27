package com.metro.metromobile.utils.manager;

import android.content.Context;
import android.content.SharedPreferences;

import static com.metro.metromobile.utils.keys.PreferenceKeys.PREFERENCE_KEY_APP_NAME;

public class TokenManager {

    private static Context mAppContext;

    // Prevent instantiation
    private TokenManager() { }

    public static void init(Context appContext) {
        mAppContext = appContext;
    }

    private static SharedPreferences getSharedPreferences() {
        return mAppContext.getSharedPreferences(PREFERENCE_KEY_APP_NAME, Context.MODE_PRIVATE);
    }

}