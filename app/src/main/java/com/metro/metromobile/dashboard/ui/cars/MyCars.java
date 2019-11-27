package com.metro.metromobile.dashboard.ui.cars;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyCars {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<MyCar> data ;
    @SerializedName("message")
    @Expose
    private String message;

    public MyCars(Boolean status, List<MyCar> data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public MyCars() {
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<MyCar> getData() {
        return data;
    }

    public void setData(List<MyCar> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
