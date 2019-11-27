package com.metro.metromobile.utils.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.widget.AppCompatTextView;

public class Tools {

    public static void setDimStatusBar(Activity act) {
        View decorView = act.getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_LOW_PROFILE;
        decorView.setSystemUiVisibility(uiOptions);
    }

    public static void clearStatusBarDim(Activity act) {
        View decorView = act.getWindow().getDecorView();
        decorView.setSystemUiVisibility(0);
    }

    public static void setSystemBarTransparent(Activity act) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public static void setTextViewDrawableColor(AppCompatTextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
            }
        }
    }

//    public static int getThemedPrimaryColor(Context context) {
//        TypedValue typedValue = new TypedValue();
//        Resources.Theme theme = context.getTheme();
//        theme.resolveAttribute(R.attr.themePrimaryTextColor, typedValue, true);
//        return typedValue.data;
//    }
}