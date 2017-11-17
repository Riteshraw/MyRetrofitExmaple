package com.example.project.myretrofitexmaple.rest;

import com.example.project.myretrofitexmaple.model.Login;
import com.example.project.myretrofitexmaple.model.User;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by ritesh on 13-11-2017.
 */

public interface ApiInterface {
    @GET("Login/GetUserInfoByMobileNo")
    Call<User> getUserDetails(@Query("MobileNo") String userMobile);

    @POST("Login/ValidateUser")
    Call<User> postValidateUser(@Body Login login);

    @Multipart
    @POST("Login/UpdateUserInfo")
    Call<ResponseBody> updateUserDetails(
            @Part("body") RequestBody body,
            @Part MultipartBody.Part photo
    );

    @Multipart
    @POST("Login/UpdateUserInfo")
    Call<ResponseBody> updateUserDetails2(
            @Part("DisplayName") RequestBody DisplayName,
            @Part("Email") RequestBody Email,
            @Part("Mobile") RequestBody Mobile,
            @Part("Password") RequestBody Password,
            @Part("DeviceID") RequestBody DeviceID,
            @Part("Token") RequestBody Token,
            @Part("DOB") RequestBody DOB,
            @Part MultipartBody.Part photo
    );
}
