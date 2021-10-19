package com.example.bartending_drink_app.model;

public class CartResponse {
    private Cart data;

    public CartResponse(Cart data) {
        this.data = data;
    }

    public Cart getData() {
        return data;
    }

    public void setData(Cart data) {
        this.data = data;
    }


}