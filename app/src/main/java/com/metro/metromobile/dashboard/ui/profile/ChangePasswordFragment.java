package com.metro.metromobile.dashboard.ui.profile;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.metro.metromobile.R;
import com.metro.metromobile.injector.Injector;
import com.metro.metromobile.model.request.UpdatePasswordRequest;
import com.metro.metromobile.model.response.BaseResponse;
import com.metro.metromobile.rest.ServiceGenerator;
import com.metro.metromobile.shared.base.BaseFragment;
import com.metro.metromobile.shared.dialogs.CustomProgressDialog;
import com.metro.metromobile.utils.app.ServerUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChangePasswordFragment extends BaseFragment {

    AppCompatEditText currentPassword, newPassword, newPasswordAgain;
    AppCompatButton changePasswordButton;
    CustomProgressDialog customProgressDialog;


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_change_password;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        currentPassword = view.findViewById(R.id.current_password_view);
        newPassword = view.findViewById(R.id.new_password_view);
        newPasswordAgain = view.findViewById(R.id.confirm_password_view);
        changePasswordButton = view.findViewById(R.id.change_password_button);

        changePasswordButton.setOnClickListener(view1 -> {
            doProcessUserChangePassword(
                    currentPassword.getText().toString(),
                    newPassword.getText().toString(),
                    newPasswordAgain.getText().toString()
            );
        });
    }

    public void doProcessUserChangePassword(String currentPassword, String newPassword, String confirmPassword) {
        if (TextUtils.isEmpty(currentPassword)) {
            showErrorMessageToast(getString(R.string.current_password_require));
            return;
        }

        if (TextUtils.isEmpty(newPassword)) {
            showErrorMessageToast(getString(R.string.new_password_require));
            return;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            showErrorMessageToast(getString(R.string.confirm_password_require));
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            showErrorMessageToast(getString(R.string.no_match_password));
            return;
        }

        if (ServerUtils.isNetworkUnavailable(getContext())) {
            showErrorMessageToast(getString(R.string.no_network_connection));
            return;
        }

        UpdatePasswordRequest body = new UpdatePasswordRequest();
        body.setCurrent_password(currentPassword);
        body.setConfirm_password(newPassword);
        body.setPassword(newPassword);


        doSendDetailsToServer(body);
    }

    private void doSendDetailsToServer(UpdatePasswordRequest body) {
        customProgressDialog.showDialog();
        ServiceGenerator service = Injector.provideRetrofit().create(ServiceGenerator.class);
        Call<BaseResponse> call = service.updatePassword(body);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                customProgressDialog.hideDialog();
                processResponse(response);
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                customProgressDialog.hideDialog();
                showErrorMessageToast(getString(R.string.error_text));
            }
        });
    }

    private void processResponse(Response<BaseResponse> response) {
        if (response != null) {
            if (response.body().getStatus()) {
                showSuccessMessageToast("Successfully updated password");
                super.getActivity().onBackPressed();
            } else {
                showErrorMessageToast(response.body().getMessage());
            }
        } else {
            showErrorMessageToast(getString(R.string.error_text));
        }
    }
}
