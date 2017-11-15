package com.example.project.myretrofitexmaple.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

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
    String mCurrentPhotoPath;
    private ImageView imageView;
    File photoFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getExample();
//        postExample();

        (findViewById(R.id.btn_img)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = null;
                dispatchTakePictureIntent();
//                imagePostExample(uri);
            }
        });

        imageView = (ImageView) findViewById(R.id.imageView);

    }

    private void getExample() {
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

    private void postExample() {
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

    private void imagePostExample(Uri uri) {
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

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (photoFile != null) {
//                Uri photoURI = FileProvider.getUriForFile(this,"com.example.android.fileprovider",photoFile);
                Uri photoURI = Uri.fromFile(photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            imageView.setImageBitmap(imageBitmap);
            imageView.setImageURI(Uri.fromFile(photoFile));
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        mCurrentPhotoPath = image.getAbsolutePath();
        return image;

    }
}
