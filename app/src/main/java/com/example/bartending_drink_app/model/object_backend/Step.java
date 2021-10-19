package com.example.bartending_drink_app.model.object_backend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Step {
    @SerializedName("id")
    @Expose
    long id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("banner_img")
    @Expose
    String banner_img;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("order")
    @Expose
    int order;

    @SerializedName("blog_id")
    @Expose
    int blog_id;

    public Step(long id, String name, String banner_img, String description, int order, int blog_id) {
        this.id = id;
        this.name = name;
        this.banner_img = banner_img;
        this.description = description;
        this.order = order;
        this.blog_id = blog_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBanner_img() {
        return banner_img;
    }

    public void setBanner_img(String banner_img) {
        this.banner_img = banner_img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
