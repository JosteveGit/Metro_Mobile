package com.metro.metromobile.activities;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.metro.metromobile.R;
import com.metro.metromobile.shared.base.BaseActivity;
import com.metro.metromobile.utils.app.ServerUtils;
import com.metro.metromobile.utils.app.ValidationUtils;

import butterknife.BindView;

public class ForgotPasswordActivity extends BaseActivity {

    @BindView(R.id.password_email_view)
    AppCompatAutoCompleteTextView passwordEmailView;

    @BindView(R.id.reset_password_button)
    AppCompatButton resetPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onRegisterButtonClicked(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }

    public void onResetPasswordClick(View view) {
        String email = passwordEmailView.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            showErrorMessageToast(getString(R.string.emaill_require));
            return;
        }

        if (ValidationUtils.isInvalidEmailAddress(email)) {
            showErrorMessageToast(getString(R.string.email_invalid));
            return;
        }

        if (ServerUtils.isNetworkUnavailable(this)) {
            showErrorMessageToast(getString(R.string.no_network_connection));
            return;
        }
    }

//    private void processResponse(ServerResponse response) {
//        if (response != null) {
//            if (response.getStatus()) {
//                showSuccessMessageToast(response.getMessage());
//            } else {
//                showErrorMessageToast(response.getMessage());
//            }
//        } else {
//            showErrorMessageToast(getString(R.string.error_text));
//        }
//    }
    public void doGoBackToLogin(View view) { super.onBackPressed(); }
}
