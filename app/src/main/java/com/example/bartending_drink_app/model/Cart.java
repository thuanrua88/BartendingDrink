package com.example.bartending_drink_app.model;

import java.util.ArrayList;

public class Cart {
    private Integer id;
    private Integer user_id;
    private String phone;
    private String address;
    private String comment;
    private Integer status_id;
    private ArrayList<ItemCart> items;

    public  float  getTotalPrice() {
        float total = 0f;
        for (ItemCart itemCart : getItems()) {
            total += itemCart.getDish().getPrice() * itemCart.getQuantity();
        }
        return total;
    }

    public Cart(Integer id, Integer user_id, String phone, String address, String comment, Integer status_id, ArrayList<ItemCart> items) {
        this.id = id;
        this.user_id = user_id;
        this.phone = phone;
        this.address = address;
        this.comment = comment;
        this.status_id = status_id;
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Integer status_id) {
        this.status_id = status_id;
    }

    public ArrayList<ItemCart> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemCart> items) {
        this.items = items;
    }
}
