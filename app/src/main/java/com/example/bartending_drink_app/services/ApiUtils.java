package com.example.bartending_drink_app.services;

public class ApiUtils {
    public static final String BASE_URL = "http://192.168.20.103:8888/api/";

    public static SOService getInstance(){
        return RetrofitClient.getRetrofit(BASE_URL).create(SOService.class);
    }
}
