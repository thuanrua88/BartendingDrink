package com.example.bartending_drink_app.model.object_backend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {
    @SerializedName("id")
    @Expose
    long id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("urlSlug")
    @Expose
    String urlSlug;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("posts")
    @Expose
    List<Post> posts;

    public Category(long id, String name, String urlSlug, String description, List<Post> posts) {
        this.id = id;
        this.name = name;
        this.urlSlug = urlSlug;
        this.description = description;
        this.posts = posts;
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

    public String getUrlSlug() {
        return urlSlug;
    }

    public void setUrlSlug(String urlSlug) {
        this.urlSlug = urlSlug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
