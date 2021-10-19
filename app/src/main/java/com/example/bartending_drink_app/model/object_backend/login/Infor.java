package com.example.bartending_drink_app.model.object_backend.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Infor {
    @SerializedName("ContentType")
    @Expose
    private Object contentType;
    @SerializedName("SerializerSettings")
    @Expose
    private Object serializerSettings;
    @SerializedName("StatusCode")
    @Expose
    private Object statusCode;
    @SerializedName("Value")
    @Expose
    private Value value;

    public Object getContentType() {
        return contentType;
    }

    public void setContentType(Object contentType) {
        this.contentType = contentType;
    }

    public Object getSerializerSettings() {
        return serializerSettings;
    }

    public void setSerializerSettings(Object serializerSettings) {
        this.serializerSettings = serializerSettings;
    }

    public Object getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Object statusCode) {
        this.statusCode = statusCode;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
