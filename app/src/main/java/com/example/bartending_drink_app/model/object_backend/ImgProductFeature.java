package com.example.bartending_drink_app.model.object_backend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImgProductFeature {

    @SerializedName("id")
            @Expose
    long id;

    @SerializedName("product_id")
            @Expose
    long product_id;

    @SerializedName("avatar_feature")
            @Expose
    String avatar_feature;

    public ImgProductFeature(long id, long product_id, String avatar_feature) {
        this.id = id;
        this.product_id = product_id;
        this.avatar_feature = avatar_feature;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public String getAvatar_feature() {
        return avatar_feature;
    }

    public void setAvatar_feature(String avatar_feature) {
        this.avatar_feature = avatar_feature;
    }
}
