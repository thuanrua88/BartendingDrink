package com.example.bartending_drink_app.model.object_backend.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseLogin {
    @SerializedName("Infor")
    @Expose
    private Infor infor;
    @SerializedName("Token")
    @Expose
    private String token;
    @SerializedName("Success")
    @Expose
    private Boolean success;
    @SerializedName("Errors")
    @Expose
    private Object errors;

    public Infor getInfor() {
        return infor;
    }

    public void setInfor(Infor infor) {
        this.infor = infor;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
