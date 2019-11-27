package com.metro.metromobile.model.request;

public class AddBookingRequest {
    int car_id;
    String service_type,service_day,service_time,pickup_address,additional_note, contact_person, contact_phone;

    public AddBookingRequest(int car_id, String service_type, String service_day, String service_time, String pickup_address, String additional_note, String contact_person, String contact_phone) {
        this.car_id = car_id;
        this.service_type = service_type;
        this.service_day = service_day;
        this.service_time = service_time;
        this.pickup_address = pickup_address;
        this.additional_note = additional_note;
        this.contact_person = contact_person;
        this.contact_phone = contact_phone;
    }

    public AddBookingRequest() {
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getService_day() {
        return service_day;
    }

    public void setService_day(String service_day) {
        this.service_day = service_day;
    }

    public String getService_time() {
        return service_time;
    }

    public void setService_time(String service_time) {
        this.service_time = service_time;
    }

    public String getPickup_address() {
        return pickup_address;
    }

    public void setPickup_address(String pickup_address) {
        this.pickup_address = pickup_address;
    }

    public String getAdditional_note() {
        return additional_note;
    }

    public void setAdditional_note(String additional_note) {
        this.additional_note = additional_note;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }
}
