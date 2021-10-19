package com.example.bartending_drink_app.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Base64;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Arrays;
import java.util.Calendar;

public class Functions {
    public static final int REQUEST_PERMISSION_STORAGE = 1010;
    public static final int REQUEST_IMAGE = 1011;
    public static void showMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean checkPermission(Activity activity) {
        int readStorage = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        int camera = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        if (readStorage != PackageManager.PERMISSION_GRANTED || camera != PackageManager.PERMISSION_GRANTED)
            return false;
        return true;
    }

    public static void requestPermission(Activity activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Arrays.toString(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}))) {
            Toast.makeText(activity, "Allow access camera and storage?", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA
            }, REQUEST_PERMISSION_STORAGE);
        }
    }

    public static String getCurrentTime(){
        String time = "";
        Calendar calendar = Calendar.getInstance();
        String day = calendar.get(Calendar.DAY_OF_MONTH)<10?"0" +  calendar.get(Calendar.DAY_OF_MONTH) :  calendar.get(Calendar.DAY_OF_MONTH)+"";
        String month = (calendar.get(Calendar.MONTH) + 1)<10?"0" + (calendar.get(Calendar.MONTH) + 1) : (calendar.get(Calendar.MONTH) + 1) +"";
        String year = calendar.get(Calendar.YEAR)+"";
        time = month +"-" + day + "-" + year;

        return time;
    }

    public static boolean checkToken(Context context){
        SessionAccount sessionAccount = new SessionAccount(context);
        return sessionAccount.getString(SessionAccount.TOKEN)!=null;
    }

    public static Bitmap convertFromBase64endCode(String encode){
        //tring base64Image = encode.split(",")[1];
        byte[] decodedString = Base64.decode(encode, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        return decodedByte;
    }


}
