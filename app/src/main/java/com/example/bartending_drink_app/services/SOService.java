package com.example.bartending_drink_app.services;

import com.example.bartending_drink_app.model.object_backend.blogers.Blog;
import com.example.bartending_drink_app.model.object_backend.blogers.BlogAllResponse;
import com.example.bartending_drink_app.model.object_backend.blogers.BlogResponse;
import com.example.bartending_drink_app.model.object_backend.login.RequestLogin;
import com.example.bartending_drink_app.model.object_backend.register.RegisterRequest;
import com.example.bartending_drink_app.model.object_backend.login.ResponseLogin;
import com.example.bartending_drink_app.model.object_backend.register.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SOService {

    @Headers({
            "Accept: */*",
            "Content-Type: application/json-patch+json"
    })
    @POST("Authen/Login")
    Call<ResponseLogin> GetResponseLogin(@Body RequestLogin requestLogin);


    @Headers({
            "Accept: */*",
            "Content-Type: application/json-patch+json"
    })
    @POST("Authen/Register")
    Call<ResponseLogin> GetResponseRegister(@Body RegisterRequest registerRequest);

    @Headers("accept: text/plain")
    @GET("Blog/get-all-blog")
    Call<BlogAllResponse> GetAllBlogs();


    @Headers({
            "accept: text/plain",
            "Content-Type: application/json-patch+json",
    })
    @POST("Blog")
    Call<Blog> PostBlog(@Body Blog blog);


    @Headers("accept: text/plain")
    @GET("http://192.168.0.108:8888/api/Blog/detail/{id}")
    Call<BlogResponse> GetBlogByID(@Path("id") String id);
    


}
