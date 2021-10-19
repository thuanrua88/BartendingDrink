package com.example.bartending_drink_app.model;

import java.util.ArrayList;

public class HistoryCartResponse {
    private ArrayList<Cart> data;

    public ArrayList<Cart> getData() {
        return data;
    }

    public void setData(ArrayList<Cart> data) {
        this.data = data;
    }
}
