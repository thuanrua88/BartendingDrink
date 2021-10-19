package com.example.bartending_drink_app.model;

import java.util.ArrayList;

public class CategoryResponse {
    private ArrayList<CategoryDomain> data;

    public ArrayList<CategoryDomain> getData() {
        return data;
    }

    public void setData(ArrayList<CategoryDomain> data) {
        this.data = data;
    }
}
