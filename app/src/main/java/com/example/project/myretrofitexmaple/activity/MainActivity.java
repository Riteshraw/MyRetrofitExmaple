package com.example.project.myretrofitexmaple.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.project.myretrofitexmaple.R;
import com.example.project.myretrofitexmaple.model.User;
import com.example.project.myretrofitexmaple.rest.ApiClient;
import com.example.project.myretrofitexmaple.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    //http://answerandwin.nubiz.co.in/api/Login/GetUserInfoByMobileNo?MobileNo=9716927111
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<User> call = apiInterface.getUserDetails("9716927111");

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                ((TextView) findViewById(R.id.txt)).setText(response.body().getUserName());
                Log.d(TAG, "User Info : " + response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

    }
}
