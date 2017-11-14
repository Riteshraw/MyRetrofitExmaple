package com.example.project.myretrofitexmaple.model;

/**
 * Created by ritesh on 14-11-2017.
 */

public class Login {

    String mobile;
    String password;
    String deviceId;
    String token;
    String userName;
    String userId;

    public Login(String mobile, String password, String deviceId, String token) {
        this.mobile = mobile;
        this.password = password;
        this.deviceId = deviceId;
        this.token = token;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
