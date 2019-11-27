package com.metro.metromobile.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyBookings implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("service_type")
    @Expose
    private String serviceType;

    @SerializedName("contact_person")
    @Expose
    private String contactPerson;

    @SerializedName("contact_phone")
    @Expose
    private String contactPhone;

    @SerializedName("service_day")
    @Expose
    private String serviceDay;
    @SerializedName("service_time")
    @Expose
    private String serviceTime;
    @SerializedName("pickup_address")
    @Expose
    private String pickupAddress;
    @SerializedName("booking_status")
    @Expose
    private String bookingStatus;
    @SerializedName("pay_status")
    @Expose
    private String payStatus;
    @SerializedName("additional_note")
    @Expose
    private String additionalNote;
    @SerializedName("created_on")
    @Expose
    private String createdOn;
    @SerializedName("car")
    @Expose
    private List<Car> car ;
    @SerializedName("chats")
    @Expose
    private List<Chat> chats;


    public static final Creator<MyBookings> CREATOR = new Creator<MyBookings>() {
        @Override
        public MyBookings createFromParcel(Parcel in) {
            return new MyBookings(in);
        }

        @Override
        public MyBookings[] newArray(int size) {
            return new MyBookings[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceDay() {
        return serviceDay;
    }

    public void setServiceDay(String serviceDay) {
        this.serviceDay = serviceDay;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getAdditionalNote() {
        return additionalNote;
    }

    public void setAdditionalNote(String additionalNote) {
        this.additionalNote = additionalNote;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public List<Car> getCar() {
        return car;
    }

    public void setCar(List<Car> car) {
        this.car = car;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.serviceType);
        dest.writeString(this.additionalNote);
        dest.writeString(this.bookingStatus);
        dest.writeString(this.createdOn);
        dest.writeString(this.payStatus);
        dest.writeString(this.pickupAddress);
        dest.writeString(this.serviceDay);
        dest.writeString(this.serviceTime);
        dest.writeString(this.contactPerson);
        dest.writeString(this.contactPhone);
    }
    protected MyBookings(Parcel in) {
        id = in.readString();
        serviceType = in.readString();
        additionalNote = in.readString();
        bookingStatus = in.readString();
        createdOn = in.readString();
        payStatus = in.readString();
        pickupAddress = in.readString();
        serviceDay = in.readString();
        serviceTime = in.readString();
        contactPerson = in.readString();
        contactPhone = in.readString();
    }


    public  class Car {
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
        @SerializedName("chasis_no")
        @Expose
        private String chasisNo;
        @SerializedName("last_service")
        @Expose
        private Object lastService;
        @SerializedName("car_status")
        @Expose
        private String carStatus;
        @SerializedName("car_image")
        @Expose
        private String carImage;
        @SerializedName("created_on")
        @Expose
        private String createdOn;

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

        public String getChasisNo() {
            return chasisNo;
        }

        public void setChasisNo(String chasisNo) {
            this.chasisNo = chasisNo;
        }

        public Object getLastService() {
            return lastService;
        }

        public void setLastService(Object lastService) {
            this.lastService = lastService;
        }

        public String getCarStatus() {
            return carStatus;
        }

        public void setCarStatus(String carStatus) {
            this.carStatus = carStatus;
        }

        public String getCarImage() {
            return carImage;
        }

        public void setCarImage(String carImage) {
            this.carImage = carImage;
        }

        public String getCreatedOn() {
            return createdOn;
        }

        public void setCreatedOn(String createdOn) {
            this.createdOn = createdOn;
        }
    }

    public  class Chat {
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("booking_id")
        @Expose
        private String bookingId;
        @SerializedName("sender")
        @Expose
        private String sender;
        @SerializedName("msg")
        @Expose
        private String msg;
        @SerializedName("created_on")
        @Expose
        private String createdOn;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBookingId() {
            return bookingId;
        }

        public void setBookingId(String bookingId) {
            this.bookingId = bookingId;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getCreatedOn() {
            return createdOn;
        }

        public void setCreatedOn(String createdOn) {
            this.createdOn = createdOn;
        }
    }
}
