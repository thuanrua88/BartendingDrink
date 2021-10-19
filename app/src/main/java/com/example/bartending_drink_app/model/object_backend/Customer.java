package com.example.bartending_drink_app.model.object_backend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {

    @SerializedName("id")
    @Expose
    String id;

    @SerializedName("id")
    @Expose
    String fullName;

    @SerializedName("isAdmin")
    @Expose
    boolean isAdmin;

    @SerializedName("isShipper")
    @Expose
    boolean isShipper;

    @SerializedName("isActive")
    @Expose
    boolean isActive;

    @SerializedName("type")
    @Expose
    int type;

    @SerializedName("status")
    @Expose
    boolean status;

    public Customer(String id, String fullName, boolean isAdmin, boolean isShipper, boolean isActive, int type, boolean status) {
        this.id = id;
        this.fullName = fullName;
        this.isAdmin = isAdmin;
        this.isShipper = isShipper;
        this.isActive = isActive;
        this.type = type;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isShipper() {
        return isShipper;
    }

    public void setShipper(boolean shipper) {
        isShipper = shipper;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
