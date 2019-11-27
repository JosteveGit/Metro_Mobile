package com.metro.metromobile.shared.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.metro.metromobile.R;

import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;


@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    public void setToolbarConfigNoTitle(Boolean value) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(value);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.round_chevron_left_24);
        }
    }

    public void changeToolbarTitle(int title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    public void changeToolbarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    public void showPlainMessageToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showErrorMessageToast(String msg) {
        Toasty.error(this, msg, Toast.LENGTH_LONG, true).show();
    }

    public void showSuccessMessageToast(String msg) {
        Toasty.success(this, msg, Toast.LENGTH_SHORT, true).show();
    }

    public void showWarningMessageToast(String msg) {
        Toasty.warning(this, msg, Toast.LENGTH_SHORT, true).show();
    }

    public void showInfoMessageToast(String msg) {
        Toasty.info(this, msg, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
