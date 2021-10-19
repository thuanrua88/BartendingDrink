package com.example.bartending_drink_app.model;

public class Material {
    private String titleMaterial;
    private int imageMaterial;

    public Material(String titleMaterial, int imageMaterial) {
        this.titleMaterial = titleMaterial;
        this.imageMaterial = imageMaterial;
    }

    public String getTitleMaterial() {
        return titleMaterial;
    }

    public void setTitleMaterial(String titleMaterial) {
        this.titleMaterial = titleMaterial;
    }

    public int getImageMaterial() {
        return imageMaterial;
    }

    public void setImageMaterial(int imageMaterial) {
        this.imageMaterial = imageMaterial;
    }
}
