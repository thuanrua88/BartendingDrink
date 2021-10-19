package com.example.bartending_drink_app.model.request;

public class AddItemCardParam {
    private String product_id;

    public AddItemCardParam(String product_id) {
        this.product_id = product_id;
    }

    public String getProductId() {
        return product_id;
    }

    public void setProductId(String product_id) {
        this.product_id = product_id;
    }
}
