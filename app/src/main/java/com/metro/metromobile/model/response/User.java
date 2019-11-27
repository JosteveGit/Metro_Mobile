package com.metro.metromobile.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    private String id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("ref_by")
    @Expose
    private String refBy;
    @SerializedName("balance")
    @Expose
    private String balance;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("activation_token")
    @Expose
    private String activationToken;
    @SerializedName("user_code")
    @Expose
    private String userCode;
    @SerializedName("user_active")
    @Expose
    private String userActive;
    @SerializedName("is_verified")
    @Expose
    private String isVerified;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("date_registered")
    @Expose
    private String dateRegistered;
    @SerializedName("last_login")
    @Expose
    private String lastLogin;
    @SerializedName("last_ip")
    @Expose
    private String lastIp;
    @SerializedName("created_on")
    @Expose
    private String createdOn;
    @SerializedName("modified_on")
    @Expose
    private String modifiedOn;

    @SerializedName("invite_title")
    @Expose
    private String inviteTitle;

    @SerializedName("invite_desc")
    @Expose
    private String inviteDesc;

    public User() {
    }

    public User(String id, String email, String firstName, String lastName, String about, String phone, String refBy, String balance, String avatar, String rating, String token, String activationToken, String userCode, String userActive, String isVerified, String userType, String dateRegistered, String lastLogin, String lastIp, String createdOn, String modifiedOn) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.about = about;
        this.phone = phone;
        this.refBy = refBy;
        this.balance = balance;
        this.avatar = avatar;
        this.rating = rating;
        this.token = token;
        this.activationToken = activationToken;
        this.userCode = userCode;
        this.userActive = userActive;
        this.isVerified = isVerified;
        this.userType = userType;
        this.dateRegistered = dateRegistered;
        this.lastLogin = lastLogin;
        this.lastIp = lastIp;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRefBy() {
        return refBy;
    }

    public void setRefBy(String refBy) {
        this.refBy = refBy;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getActivationToken() {
        return activationToken;
    }

    public void setActivationToken(String activationToken) {
        this.activationToken = activationToken;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserActive() {
        return userActive;
    }

    public void setUserActive(String userActive) {
        this.userActive = userActive;
    }

    public String getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(String isVerified) {
        this.isVerified = isVerified;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(String dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getInviteTitle() {
        return inviteTitle;
    }

    public void setInviteTitle(String inviteTitle) {
        this.inviteTitle = inviteTitle;
    }

    public String getInviteDesc() {
        return inviteDesc;
    }

    public void setInviteDesc(String inviteDesc) {
        this.inviteDesc = inviteDesc;
    }
}
