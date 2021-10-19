package com.example.bartending_drink_app.model.object_backend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    @Expose
    long id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("banner_img")
    @Expose
    String banner_img;

    @SerializedName("cover_img")
    @Expose
    String cover_img;

    @SerializedName("price")
    @Expose
    float price;

    @SerializedName("sale")
    @Expose
    float sale;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("availability")
    @Expose
    int availability;

    @SerializedName("unit")
    @Expose
    String unit;

    @SerializedName("storage_instructions")
    @Expose
    String storage_instructions;

    @SerializedName("view")
    @Expose
    int view;

    @SerializedName("revenue")
    @Expose
    float revenue;

    @SerializedName("returned")
    @Expose
    int returned;

    @SerializedName("status")
    @Expose
    int status;

    @SerializedName("seller_id")
    @Expose
    String seller_id;

    @SerializedName("category_id")
    @Expose
    int category_id;

    @SerializedName("create_at")
    @Expose
    String create_at;

    @SerializedName("update_at")
    @Expose
    String update_at;


    public Product() {
    }

    public Product(long id, String name, String banner_img, String cover_img, float price, float sale, String description, int availability, String unit, String storage_instructions, int view, float revenue, int returned, int status, String seller_id, int category_id, String create_at, String update_at) {
        this.id = id;
        this.name = name;
        this.banner_img = banner_img;
        this.cover_img = cover_img;
        this.price = price;
        this.sale = sale;
        this.description = description;
        this.availability = availability;
        this.unit = unit;
        this.storage_instructions = storage_instructions;
        this.view = view;
        this.revenue = revenue;
        this.returned = returned;
        this.status = status;
        this.seller_id = seller_id;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getSale() {
        return sale;
    }

    public void setSale(float sale) {
        this.sale = sale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStorage_instructions() {
        return storage_instructions;
    }

    public void setStorage_instructions(String storage_instructions) {
        this.storage_instructions = storage_instructions;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
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
