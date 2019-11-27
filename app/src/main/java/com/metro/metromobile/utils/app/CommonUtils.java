package com.metro.metromobile.utils.app;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import androidx.annotation.NonNull;

import com.metro.metromobile.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CommonUtils {

    public static String getUserFullName(String userFirstName, String userLastName) {
        String firstName = "", lastName = "";
        if (!TextUtils.isEmpty(userFirstName)) {
            firstName = userFirstName.trim();
        }

        if (!TextUtils.isEmpty(userLastName)) {
            lastName = userLastName.trim();
        }

        return String.format("%s %s", lastName, firstName);
    }

    public static String doSentenceCase(String text) {
        if (TextUtils.isEmpty(text)) {
            return "";
        }
        char[] array = text.toCharArray();
        array[0] = Character.toUpperCase(array[0]);
        return new String(array);
    }

    private static String doParseServerTime(Date serverDate) {
        String outputPattern = "dd MMM";
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.US);

        return outputFormat.format(serverDate);
    }

}
