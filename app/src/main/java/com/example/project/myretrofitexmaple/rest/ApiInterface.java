package com.example.project.myretrofitexmaple.rest;

import com.example.project.myretrofitexmaple.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ritesh on 13-11-2017.
 */

public interface ApiInterface {
    @GET("Login/GetUserInfoByMobileNo")
    Call<User> getUserDetails(@Query("MobileNo") String userMobile);
}
