package com.metro.metromobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.metro.metromobile.R;
import com.metro.metromobile.dashboard.DashboardActivity;
import com.metro.metromobile.injector.Injector;
import com.metro.metromobile.model.RegisterRequestBody;
import com.metro.metromobile.model.response.AuthenticationResponse;
import com.metro.metromobile.rest.ServiceGenerator;
import com.metro.metromobile.shared.base.BaseActivity;
import com.metro.metromobile.shared.dialogs.CustomProgressDialog;
import com.metro.metromobile.utils.app.ServerUtils;
import com.metro.metromobile.utils.app.ValidationUtils;
import com.metro.metromobile.utils.manager.SessionManager;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscription;


public class RegisterActivity extends BaseActivity {
    private CustomProgressDialog customProgressDialog;

    @BindView(R.id.register_fname_view)
    AppCompatEditText firstNameView;

    @BindView(R.id.register_lname_view)
    AppCompatEditText lastNameView;

    @BindView(R.id.register_email_view)
    AppCompatAutoCompleteTextView registerEmailView;

    @BindView(R.id.register_password_view)
    AppCompatEditText registerPasswordView;

    @BindView(R.id.register_phone_view)
    AppCompatEditText registerPhoneView;

    @BindView(R.id.register_button)
    AppCompatButton registerButton;

    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        customProgressDialog = new CustomProgressDialog(this);
    }


    public void goBack(View view) {
        super.onBackPressed();
    }

        public void doProcessUserRegistration(View view) {
            String firstName = firstNameView.getText().toString().trim();
            String lastName = lastNameView.getText().toString().trim();
            String email = registerEmailView.getText().toString().trim();
            String password = registerPasswordView.getText().toString().trim();
            String phone = registerPhoneView.getText().toString().trim();


            if (TextUtils.isEmpty(firstName)) {
                showErrorMessageToast(getString(R.string.first_name_required));
                return;
            }

            if (TextUtils.isEmpty(lastName)) {
                showErrorMessageToast(getString(R.string.last_name_required));
                return;
            }

            if (TextUtils.isEmpty(email)) {
                showErrorMessageToast(getString(R.string.emaill_require));
                return;
            }

            if (ValidationUtils.isInvalidEmailAddress(email)) {
                showErrorMessageToast(getString(R.string.email_invalid));
                return;
            }

            if (TextUtils.isEmpty(password)) {
                showErrorMessageToast(getString(R.string.password_require));
                return;
            }

            if (ServerUtils.isNetworkUnavailable(this)) {
                showErrorMessageToast(getString(R.string.no_network_connection));
                return;
            }

            RegisterRequestBody body = new RegisterRequestBody();
            body.setFirst_name(firstName);
            body.setLast_name(lastName);
            body.setEmail(email);
            body.setPassword(password);
            body.setPhone(phone);
            doSendDetailsToServer(body);
    }

    private void doSendDetailsToServer(RegisterRequestBody body) {
        customProgressDialog.showDialog();
        ServiceGenerator service = Injector.provideRetrofit().create(ServiceGenerator.class);
        Call<AuthenticationResponse> call = service.registerUser(body);
        call.enqueue(new Callback<AuthenticationResponse>() {
            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                customProgressDialog.hideDialog();
                processResponse(response);
            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                customProgressDialog.hideDialog();
                Log.d("RegError", t.getLocalizedMessage());
                showErrorMessageToast(getString(R.string.error_text));
            }
        });

//        subscription = appRemoteDataStore.registerUser(body)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<RegisterResponse>() {
//                    @Override
//                    public void onCompleted() {}
//
//                    @Override
//                    public void onError(Throwable e) {
//                       customProgressDialog.hideDialog();
//                        Log.d("RegError", e.getLocalizedMessage());
//                        showErrorMessageToast(getString(R.string.error_text));
//                    }
//
//                    @Override
//                    public void onNext(RegisterResponse response) {
//                        customProgressDialog.hideDialog();
//                        processResponse(response);
//                    }
//                });
    }

    private void processResponse(Response<AuthenticationResponse> response) {
        if (response != null) {
            if (response.isSuccessful()) {
                showSuccessMessageToast(getString(R.string.registration_successful));
                startActivity(new Intent(this, DashboardActivity.class));
                SessionManager.loginUser(response.body().getUser());
            } else {
                showErrorMessageToast(response.body().getMessage());
            }
        } else {
            showErrorMessageToast(getString(R.string.error_text));
        }
    }


}