package com.example.bartending_drink_app.model.object_backend.blogers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BlogResponse {
    @SerializedName("Message")
    @Expose
    String message;

    @SerializedName("Data")
    @Expose
    Blog data;

    public BlogResponse(String message, Blog data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Blog getData() {
        return data;
    }

    public void setData(Blog data) {
        this.data = data;
    }
}
