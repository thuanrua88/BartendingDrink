package com.example.bartending_drink_app.model.object_backend.blogers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BlogAllResponse {
    @SerializedName("Message")
    @Expose
    String message;

    @SerializedName("Data")
    @Expose
    List<Blog> data;

    public BlogAllResponse(String message, List<Blog> data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Blog> getData() {
        return data;
    }

    public void setData(List<Blog> data) {
        this.data = data;
    }
}
