package com.metro.metromobile.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Brand {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("created_on")
    @Expose
    private String createdOn;

    public Brand(String id, String brand, String logo, String createdOn) {
        this.id = id;
        this.brand = brand;
        this.logo = logo;
        this.createdOn = createdOn;
    }

    public Brand() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
}
