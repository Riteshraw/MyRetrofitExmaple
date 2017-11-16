package com.example.project.myretrofitexmaple.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.myretrofitexmaple.R;
import com.example.project.myretrofitexmaple.model.Login;
import com.example.project.myretrofitexmaple.model.User;
import com.example.project.myretrofitexmaple.rest.ApiClient;
import com.example.project.myretrofitexmaple.rest.ApiInterface;
import com.example.project.myretrofitexmaple.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    //http://answerandwin.nubiz.co.in/api/Login/ValidateUser
    //http://answerandwin.nubiz.co.in/api/Login/GetUserInfoByMobileNo?MobileNo=9716927111
    //http://answerandwin.nubiz.co.in/api/Login/AddNewUser?DisplayName=Sameer&Email=sameer@gmail.com&Mobile=9875825658&Password=12345&DeviceID=sfaf&Token=adsd&DOB=08/07/1992
    //http://answerandwin.nubiz.co.in/api/Login/UpdateUserInfo?DisplayName=Sameer&Email=sameer@gmail.com&Mobile=9874525698&Password=12345&DOB=08/07/1992

    //https://www.journaldev.com/13270/android-capture-image-camera-gallery
    //https://stackoverflow.com/questions/4989182/converting-java-bitmap-to-byte-array
    //https://developer.android.com/training/camera/photobasics.html#TaskScalePhoto

    private static final String TAG = MainActivity.class.getSimpleName();
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imageView;
    private Uri photoUri = null;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

//        retorfitGetExample();
//        retorfitPostExample();

        (findViewById(R.id.btn_img)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                photoUri = Utils.createMyImageFile(context);
//                dispatchTakePictureIntent();
                dispatchSelectPictureIntent();
                //retrofitMultipartImagePostExample(uri);

            }

        });

        imageView = (ImageView) findViewById(R.id.imageView);

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            if (photoUri != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private void dispatchSelectPictureIntent() {

        List<Intent> cameraIntents = new ArrayList<Intent>();
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = getPackageManager().queryIntentActivities(captureIntent, 0);

        for (ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(packageName);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            cameraIntents.add(intent);
        }

        // Filesystem.
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Choose file to upload");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[cameraIntents.size()]));
        startActivityForResult(chooserIntent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //for getting thumbnail from intent
            /*Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
            imageView.setImageURI(Uri.fromFile(photoUri));*/

            if (data != null)
                photoUri = data.getData();

            imageView.setImageURI(photoUri);
        }
    }

    private void retorfitGetExample() {
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

    private void retorfitPostExample() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<User> call = apiInterface.postValidateUser(
                new Login("9716927222", "12345", "c311a0ab6486321c", "eAKtiDLP0xc:APA91bF6n4YgiKpPRM0FjDMq8tEmv8j919zZhG8z5dbfavdcb4PNdiQEfodmUkLMvDjiiHMF33Za4d9qHh2SHFSWR9KnbJ0WJb-g9c-he_PPOrP3D2e3dSC_T0AXOXxYV9iEZRnvlN8t")
        );

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                Log.v("#url", user.toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void retrofitMultipartImagePostExample(Uri uri) {
        RequestBody requestBody = RequestBody.create(MultipartBody.FORM, "My data");

        File originalFile = new File(uri.getPath());

        RequestBody filePart = RequestBody.create(MediaType.parse(getContentResolver().getType(uri)), originalFile);
        MultipartBody.Part requestPhoto = MultipartBody.Part.createFormData("photo", originalFile.getName(), filePart);

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiInterface.updateUserDetails(requestBody, requestPhoto);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}
