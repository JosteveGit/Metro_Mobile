package com.metro.metromobile.utils.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.metro.metromobile.model.response.User;
import com.metro.metromobile.dashboard.ui.cars.MyCar;

import java.lang.reflect.Type;
import java.util.List;

import static com.metro.metromobile.utils.keys.PreferenceKeys.PREFERENCE_KEY_APP_NAME;
import static com.metro.metromobile.utils.keys.PreferenceKeys.PREFERENCE_KEY_INVITE_DESC;
import static com.metro.metromobile.utils.keys.PreferenceKeys.PREFERENCE_KEY_INVITE_TITLE;
import static com.metro.metromobile.utils.keys.PreferenceKeys.PREFERENCE_KEY_IS_LOGIN;
import static com.metro.metromobile.utils.keys.PreferenceKeys.PREFERENCE_KEY_IS_SEEN_LANDING;
import static com.metro.metromobile.utils.keys.PreferenceKeys.PREFERENCE_KEY_USER_BALANCE;
import static com.metro.metromobile.utils.keys.PreferenceKeys.PREFERENCE_KEY_USER_CODE;
import static com.metro.metromobile.utils.keys.PreferenceKeys.PREFERENCE_KEY_USER_EMAIL;
import static com.metro.metromobile.utils.keys.PreferenceKeys.PREFERENCE_KEY_USER_FIRST_NAME;
import static com.metro.metromobile.utils.keys.PreferenceKeys.PREFERENCE_KEY_USER_LAST_NAME;
import static com.metro.metromobile.utils.keys.PreferenceKeys.PREFERENCE_KEY_USER_PHONE;
import static com.metro.metromobile.utils.keys.PreferenceKeys.PREFERENCE_KEY_USER_PROFILE_PIC;
import static com.metro.metromobile.utils.keys.PreferenceKeys.PREFERENCE_KEY_USER_TOKEN;

public class SessionManager {

    private static Context mAppContext;

    // Prevent instantiation
    private SessionManager() { }

    public static void init(Context appContext) {
        mAppContext = appContext;
    }

    private static SharedPreferences getSharedPreferences() {
        return mAppContext.getSharedPreferences(PREFERENCE_KEY_APP_NAME, Context.MODE_PRIVATE);
    }

    public static boolean isUserLogin() {
        return getSharedPreferences().getBoolean(PREFERENCE_KEY_IS_LOGIN, false);
    }

    public static void loginUser(User user) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
//      editor.putInt(PREFERENCE_KEY_USER_ID, user.getId());
//      editor.putString(PREFERENCE_KEY_USER_TOKEN, user.getToken());
        editor.putString(PREFERENCE_KEY_USER_FIRST_NAME, user.getFirstName());
        editor.putString(PREFERENCE_KEY_USER_LAST_NAME, user.getLastName());
        editor.putString(PREFERENCE_KEY_USER_PROFILE_PIC, user.getAvatar());
        editor.putString(PREFERENCE_KEY_USER_PHONE, user.getPhone());
        editor.putString(PREFERENCE_KEY_USER_BALANCE, user.getBalance());
        editor.putString(PREFERENCE_KEY_INVITE_DESC, user.getInviteDesc());
        editor.putString(PREFERENCE_KEY_INVITE_TITLE, user.getInviteTitle());
        editor.putString(PREFERENCE_KEY_USER_CODE, user.getUserCode());
        editor.putString(PREFERENCE_KEY_USER_EMAIL, user.getEmail());
        editor.putString(PREFERENCE_KEY_USER_TOKEN, user.getToken());
        editor.putBoolean(PREFERENCE_KEY_IS_LOGIN, true);
        editor.apply();
    }

    public static void saveCarListInPreference(List<MyCar> car){
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        //Set the value
        Gson gson = new Gson();
        String carsJson = gson.toJson(car);
        editor.putString("Cars", carsJson);
        editor.apply();
    }
    public static List<MyCar> getCarsFromPreference() {
        Gson gson = new Gson();
        Type type = new TypeToken<List<MyCar>>(){}.getType();
        String carsString = getSharedPreferences().getString("Cars", "");
        return gson.fromJson(carsString, type);
    }

    public static String getUserToken() {
        return getSharedPreferences().getString(PREFERENCE_KEY_USER_TOKEN, "");
    }

    public static String getUserCode() {
        return getSharedPreferences().getString(PREFERENCE_KEY_USER_CODE, "");
    }

    public static String getUserPhone() {
        return getSharedPreferences().getString(PREFERENCE_KEY_USER_PHONE, "");
    }

    public static String getInviteTitle() {
        return getSharedPreferences().getString(PREFERENCE_KEY_INVITE_TITLE, "");
    }

    public static String getInviteDescription() {
        return getSharedPreferences().getString(PREFERENCE_KEY_INVITE_DESC, "");
    }

    public static String getFirstName() {
        return getSharedPreferences().getString(PREFERENCE_KEY_USER_FIRST_NAME, "");
    }

    public static String getLastName() {
        return getSharedPreferences().getString(PREFERENCE_KEY_USER_LAST_NAME, "");
    }
//
//    public static String getProfilePic() {
//        return getSharedPreferences().getString(PREFERENCE_KEY_USER_PROFILE_PIC, "");
//    }

    public static String getUserEmail() {
        return getSharedPreferences().getString(PREFERENCE_KEY_USER_EMAIL, "");
    }

    // Landing Page Session-------------------------------------------------------------------------
    public static void setSeenLanding() {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(PREFERENCE_KEY_IS_SEEN_LANDING, true);
        editor.apply();
    }

    public static boolean isSeenLandingScreen() {
        return getSharedPreferences().getBoolean(PREFERENCE_KEY_IS_SEEN_LANDING, false);
    }

    private static void clearSharedPreference() {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.clear();
        editor.apply();
    }

    public static void logoutUser() {
        clearSharedPreference();
        setSeenLanding();
    }
}