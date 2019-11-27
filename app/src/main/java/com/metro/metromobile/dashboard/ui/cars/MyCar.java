package com.metro.metromobile.dashboard.ui.cars;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyCar {
    @SerializedName("id")
    @Expose
    private String id;
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
    @SerializedName("last_service")
    @Expose
    private String lastService;
    @SerializedName("car_status")
    @Expose
    private String carStatus;
    @SerializedName("created_on")
    @Expose
    private String createdOn;
    @SerializedName("car_image")
    @Expose
    private String carLogo;

    @SerializedName("chasis_no")
    @Expose
    private String chasisNo;

    public MyCar() {
    }

    public String getChasisNo() {
        return chasisNo;
    }

    public void setChasisNo(String chasisNo) {
        this.chasisNo = chasisNo;
    }

    public MyCar(String id, String carName, String modelName, String modelYear, String plateNumber, String lastService, String carStatus, String createdOn, String carLogo, String chasisNo) {
        this.id = id;
        this.carName = carName;
        this.modelName = modelName;
        this.modelYear = modelYear;
        this.plateNumber = plateNumber;
        this.lastService = lastService;
        this.carStatus = carStatus;
        this.createdOn = createdOn;
        this.carLogo = carLogo;
        this.chasisNo = chasisNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getLastService() {
        return lastService;
    }

    public void setLastService(String lastService) {
        this.lastService = lastService;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getCarLogo() {
        return carLogo;
    }

    public void setCarLogo(String carLogo) {
        this.carLogo = carLogo;
    }

}
