package com.metro.metromobile.shared.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;

import com.metro.metromobile.R;

public class CustomProgressDialog {

    private Activity activity;
    private Dialog dialog;

    public CustomProgressDialog(Activity activity) {
        this.activity = activity;
        initDialog();
    }

    private void initDialog() {
        dialog  = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_progress_dialog);
    }

    public void showDialog() {
        dialog.show();
    }

    //..also create a method which will hide the dialog when some work is done
    public void hideDialog(){
        dialog.dismiss();
    }
}