package com.metro.metromobile.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthenticationResponse {
    private Boolean status;
    @Expose
    @SerializedName("data")
    private User user;
    @Expose
    @SerializedName("message")
    private String message;

    public Boolean getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }
}
