package com.example.bartending_drink_app.network;

import com.example.bartending_drink_app.model.LoginRequest;
import com.example.bartending_drink_app.model.LoginResponse;
import com.example.bartending_drink_app.model.object_backend.register.RegisterRequest;
import com.example.bartending_drink_app.model.object_backend.register.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    String SERVER_URL = "https://thawing-falls-63605.herokuapp.com/api/";

    @POST("register")
    Call<RegisterResponse> registerUsers(@Body RegisterRequest registerRequest);

    @POST("auth")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);



}
