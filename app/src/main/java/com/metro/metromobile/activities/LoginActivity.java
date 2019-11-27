package com.metro.metromobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.metro.metromobile.model.response.AuthenticationResponse;
import com.metro.metromobile.model.request.LoginRequestBody;
import com.metro.metromobile.R;
import com.metro.metromobile.dashboard.DashboardActivity;
import com.metro.metromobile.injector.Injector;
import com.metro.metromobile.rest.ServiceGenerator;
import com.metro.metromobile.shared.base.BaseActivity;
import com.metro.metromobile.shared.dialogs.CustomProgressDialog;
import com.metro.metromobile.utils.app.ServerUtils;
import com.metro.metromobile.utils.manager.SessionManager;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscription;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_email_view)
    AppCompatAutoCompleteTextView loginEmailView;

    @BindView(R.id.login_password_view)
    AppCompatEditText loginPasswordView;
    
    @BindView(R.id.login_button)
    AppCompatButton loginButton;

    private CustomProgressDialog customProgressDialog;
    private Subscription subscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponents();
//      if (SessionManager.isUserLogin()) {
//          startActivity(new Intent(this, DashboardActivity.class));
//          finish();
//      }
    }

    private void initComponents() {
        customProgressDialog = new CustomProgressDialog(this);
    }

    public void doSignUpButtonClicked(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void doProcessUserAuthentication(View view) {
        String email = loginEmailView.getText().toString().trim();
        String password = loginPasswordView.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            showErrorMessageToast(getString(R.string.emaill_require));
            return;
        }

//        if (ValidationUtils.isInvalidEmailAddress(email)) {
//            showErrorMessageToast(getString(R.string.email_invalid));
//            return;
//        }

        if (TextUtils.isEmpty(password)) {
            showErrorMessageToast(getString(R.string.password_require));
            return;
        }

        if (ServerUtils.isNetworkUnavailable(this)) {
            showErrorMessageToast(getString(R.string.no_network_connection));
            return;
        }

        doSendDetailsToServer(new LoginRequestBody(email, password));
//        String username, password;
//        Disposable d = appRemoteDataStore.loginUser().subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(loginResponse -> {
//                    //perform login and move to main screen
//                }, throwable -> {
//                    if(throwable instanceof HttpException){
//                        Log.e("Error", throwable.getMessage());
//                    }else{
//                        Log.e("Error", throwable.getMessage());
//                    }
//                });
        //
    }

    private void doSendDetailsToServer(LoginRequestBody loginRequestBody) {
        customProgressDialog.showDialog();
        ServiceGenerator service = Injector.provideRetrofit().create(ServiceGenerator.class);
        Call<AuthenticationResponse> call = service.loginUser(loginRequestBody);
        call.enqueue(new Callback<AuthenticationResponse>() {
            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                customProgressDialog.hideDialog();
                processResponse(response.body());
            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                customProgressDialog.hideDialog();
                showErrorMessageToast("Error, pls try again later");
            }
        });
    }

    private void processResponse(AuthenticationResponse response) {
        if(response != null) {
            if(response.getStatus()) {
                showSuccessMessageToast(getString(R.string.user_authenticated));
                Log.d("AppToken", response.getUser().getToken());
                SessionManager.loginUser(response.getUser());
                doShowMainScreen();
            } else {
                showErrorMessageToast(response.getMessage());
            }
        } else {
            showErrorMessageToast(getString(R.string.error_text));
        }
    }

    private void doShowMainScreen() {
        startActivity(new Intent(this, DashboardActivity.class));
        finish();
    }

    private void showProgressDialog() {
        enableFields(false);
        customProgressDialog.showDialog();
    }

    private void hideProgressDialog() {
        enableFields(true);
        customProgressDialog.hideDialog();
    }


    private void enableFields(Boolean value) {
        loginEmailView.setEnabled(value);
        loginPasswordView.setEnabled(value);
        loginButton.setEnabled(value);
    }

    public void doShowForgotPassword(View view) {
        startActivity(new Intent(this, ForgotPasswordActivity.class));
    }
}