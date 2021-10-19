package com.example.bartending_drink_app.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bartending_drink_app.model.object_backend.login.Value;

public class SessionAccount {

    public static final String USER_ID = "User_id";
    public static final String ACCOUNT = "current_account";
    public static final String FULL_NAME = "fullName";
    public static final String IS_ADMIN = "isAdmin";
    public static final String IS_SHIPPER = "isShipper";
    public static final String IS_ACTIVE = "isActive";
    public static final String TYPE = "type";
    public static final String STATUS = "status";
    public static final String TOKEN = "token";
    public static final String EMAIL = "email";
    public static final String AVATAR = "avatar";


    private static SharedPreferences share;

    public SessionAccount(Context context) {
        share = context.getSharedPreferences(ACCOUNT, Context.MODE_PRIVATE);
    }

    public void writeAccount(Value user){
        share.edit().putString(FULL_NAME, user.getFullName()).apply();
        share.edit().putString(USER_ID, user.getId()).apply();

        //share.edit().putString(AVATAR, user.get()).apply();
        share.edit().putString(EMAIL, user.getEmail()).apply();
        share.edit().putBoolean(IS_ADMIN, user.getIsAdmin()).apply();
        share.edit().putBoolean(IS_SHIPPER, user.getIsShipper()).apply();
        share.edit().putBoolean(IS_ACTIVE, user.getIsActive()).apply();
        share.edit().putInt(TYPE, user.getType()).apply();
        share.edit().putBoolean(STATUS, user.getStatus()).apply();


    }

    public void writeToken(String token){
        share.edit().putString(TOKEN, token).apply();
    }

    public void putString(String key, String value){
        share.edit().putString(key, value).apply();
    }
    public void putInt(String key, int value){
        share.edit().putInt(key, value).apply();
    }
    public void putBoolean(String key, boolean value){
        share.edit().putBoolean(key, value).apply();
    }
    public String getString(String key){
        return share.getString(key, "");
    }
    public int getInt(String key){
        return share.getInt(key, -1);
    }

    public boolean getBoolean(String key){
        return share.getBoolean(key, false);
    }

    public void clean(){
        share.edit().clear().apply();
    }


}
