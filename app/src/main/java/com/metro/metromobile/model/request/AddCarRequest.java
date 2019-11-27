package com.metro.metromobile.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddCarRequest {
    @SerializedName("car_name")
    @Expose
    private String carName;
    @SerializedName("model_name")
    @Expose
    private String modelName;
    @SerializedName("model_year")
    @Expose
    private String modelYear;
    @SerializedName("plate_number")
    @Expose
    private String plateNumber;

    @SerializedName("chasis_no")
    @Expose
    private String chassis;

    @SerializedName("car_image")
    @Expose
    private String carImage;

    public AddCarRequest(String carName, String modelName, String modelYear, String plateNumber, String chassis, String carImage) {
        this.carName = carName;
        this.modelName = modelName;
        this.modelYear = modelYear;
        this.plateNumber = plateNumber;
        this.chassis = chassis;
        this.carImage = carImage;
    }

    public AddCarRequest() {
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public String getCarImage() {
        return carImage;
    }

    public void setCarImage(String carImage) {
        this.carImage = carImage;
    }
}
