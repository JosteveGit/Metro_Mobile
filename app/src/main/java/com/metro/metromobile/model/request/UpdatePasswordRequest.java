package com.metro.metromobile.model.request;

public class UpdatePasswordRequest {
    String current_password, password, confirm_password;

    public UpdatePasswordRequest(String current_password, String password, String confirm_password) {
        this.current_password = current_password;
        this.password = password;
        this.confirm_password = confirm_password;
    }

    public UpdatePasswordRequest() {
    }

    public String getCurrent_password() {
        return current_password;
    }

    public void setCurrent_password(String current_password) {
        this.current_password = current_password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }
}
