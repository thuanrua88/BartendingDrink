package com.example.bartending_drink_app.model.object_backend.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("Id")
    @Expose
    private int id;

    @SerializedName("Username")
    @Expose
    private String username;

    @SerializedName("Email")
    @Expose
    private String email;

    @SerializedName("Success")
    @Expose
    private Boolean success;

    @SerializedName("Errors")
    @Expose
    private Object errors;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }
}
