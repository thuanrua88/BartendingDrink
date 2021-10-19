package com.example.bartending_drink_app.model.object_backend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metarial {
    @SerializedName("id")
    @Expose
    long id;

    @SerializedName("title")
    @Expose
    String title;

    @SerializedName("mass")
    @Expose
    String mass;

    @SerializedName("unit")
    @Expose
    String unit;

    @SerializedName("order")
    @Expose
    int order;

    @SerializedName("blog_id")
    @Expose
    int blog_id;

    public Metarial(long id, String title, String mass, String unit, int order, int blog_id) {
        this.id = id;
        this.title = title;
        this.mass = mass;
        this.unit = unit;
        this.order = order;
        this.blog_id = blog_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }
}
