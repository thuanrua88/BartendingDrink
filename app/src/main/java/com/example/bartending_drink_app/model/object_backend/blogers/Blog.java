package com.example.bartending_drink_app.model.object_backend.blogers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Blog {

    @SerializedName("id")
    @Expose
    long id = 0;

    @SerializedName("name")
    @Expose
    String name = "";

    @SerializedName("banner_img")
    @Expose
    String banner_img = "";

    @SerializedName("cover_img")
    @Expose
    String cover_img = "";

    @SerializedName("cooking_time")
    @Expose
    String cooking_time = "";

    @SerializedName("summary")
    @Expose
    String summary = "";

    @SerializedName("description")
    @Expose
    String description = "";

    @SerializedName("url_video_utube")
    @Expose
    String url_video_utube = "";

    @SerializedName("view")
    @Expose
    int view = 0;

    @SerializedName("status")
    @Expose
    int status = 0;

    @SerializedName("user_id")
    @Expose
    String user_id = "";

    @SerializedName("category_id")
    @Expose
    int category_id = 0;

    @SerializedName("create_at")
    @Expose
    String create_at = "";

    @SerializedName("update_at")
    @Expose
    String update_at = "";


    public Blog(){};

    public Blog(long id, String name, String banner_img, String cover_img, String cooking_time, String summary, String description, String url_video_utube, int view, int status, String user_id, int category_id, String create_at, String update_at) {
        this.id = id;
        this.name = name;
        this.banner_img = banner_img;
        this.cover_img = cover_img;
        this.cooking_time = cooking_time;
        this.summary = summary;
        this.description = description;
        this.url_video_utube = url_video_utube;
        this.view = view;
        this.status = status;
        this.user_id = user_id;
        this.category_id = category_id;
        this.create_at = create_at;
        this.update_at = update_at;
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

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public String getCooking_time() {
        return cooking_time;
    }

    public void setCooking_time(String cooking_time) {
        this.cooking_time = cooking_time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl_video_utube() {
        return url_video_utube;
    }

    public void setUrl_video_utube(String url_video_utube) {
        this.url_video_utube = url_video_utube;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }
}
