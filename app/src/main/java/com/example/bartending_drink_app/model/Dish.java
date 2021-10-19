package com.example.bartending_drink_app.model;

public class Dish {
    private Integer id;
    private String name;
    private String image_url;
    private String ingredient_des;
    private String method;
    private float price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getIngredient_des() {
        return ingredient_des;
    }

    public void setIngredient_des(String ingredient_des) {
        this.ingredient_des = ingredient_des;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
