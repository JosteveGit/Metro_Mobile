package com.metro.metromobile.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.metro.metromobile.dashboard.ui.cars.MyCar;

import java.util.List;

public class Brands {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<Brand> data ;
    @SerializedName("message")
    @Expose
    private String message;

    public Brands(Boolean status, List<Brand> data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public Brands() {
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Brand> getData() {
        return data;
    }

    public void setData(List<Brand> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
