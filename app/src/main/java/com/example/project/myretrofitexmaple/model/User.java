package com.example.project.myretrofitexmaple.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ritesh on 13-11-2017.
 */

public class User {

    @SerializedName("ImagePath")
    private String userImage;

    @SerializedName("DisplayName")
    private String userName;

    @SerializedName("Mobile")
    private String userMobile;

    @SerializedName("Email")
    private String userEmail;

    @SerializedName("Password")
    private String userPassword;

    @SerializedName("DOB")
    private String userDOB;

    @SerializedName("DeviceID")
    private String userDeviceId;

    @SerializedName("Token")
    private String userFCMToken;

    @SerializedName("Name")
    private String name;

    @SerializedName("UserGuid")
    private String userGuid;

    public User(String userImage, String userName, String userMobile, String userEmail, String userPassword, String userDOB, String userDeviceId, String userFCMToken) {
        this.userImage = userImage;
        this.userName = userName;
        this.userMobile = userMobile;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userDOB = userDOB;
        this.userDeviceId = userDeviceId;
        this.userFCMToken = userFCMToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserDOB() {
        return userDOB;
    }

    public void setUserDOB(String userDOB) {
        this.userDOB = userDOB;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserDeviceId() {
        return userDeviceId;
    }

    public void setUserDeviceId(String userDeviceId) {
        this.userDeviceId = userDeviceId;
    }

    public String getUserFCMToken() {
        return userFCMToken;
    }

    public void setUserFCMToken(String userFCMToken) {
        this.userFCMToken = userFCMToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }
}
