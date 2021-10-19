package com.example.bartending_drink_app.model;

public class FoodDomain {
    private String title;
    private int image;
    private String desc;
    private String price;
    private int numberIntCard;

    public FoodDomain(String title, int image, String price) {
        this.title = title;
        this.image = image;
        this.price = price;
    }

    public FoodDomain(String title, int image, String desc, String price, int numberIntCard) {
        this.title = title;
        this.image = image;
        this.desc = desc;
        this.price = price;
        this.numberIntCard = numberIntCard;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getNumberIntCard() {
        return numberIntCard;
    }

    public void setNumberIntCard(int numberIntCard) {
        this.numberIntCard = numberIntCard;
    }
}
