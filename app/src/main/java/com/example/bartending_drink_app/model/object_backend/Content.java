package com.example.bartending_drink_app.model.object_backend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {
    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("content")
    @Expose
    String content;

    @SerializedName("banner_img")
    @Expose
    String banner_img;

    @SerializedName("banner_cover")
    @Expose
    String banner_cover;

    @SerializedName("blog_id")
    @Expose
    int blog_id;

    public Content(int id, String name, String content, String banner_img, String banner_cover, int blog_id) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.banner_img = banner_img;
        this.banner_cover = banner_cover;
        this.blog_id = blog_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBanner_img() {
        return banner_img;
    }

    public void setBanner_img(String banner_img) {
        this.banner_img = banner_img;
    }

    public String getBanner_cover() {
        return banner_cover;
    }

    public void setBanner_cover(String banner_cover) {
        this.banner_cover = banner_cover;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }
}
