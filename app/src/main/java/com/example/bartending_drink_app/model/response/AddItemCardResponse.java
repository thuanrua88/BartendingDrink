package com.example.bartending_drink_app.model.response;

import com.example.bartending_drink_app.model.Dish;

public class AddItemCardResponse {
    private Integer id;
    private Integer quantity;
    private Integer product_id;
    private Integer card_id;
    private Dish dish;

    public AddItemCardResponse(Integer id, Integer quantity, Integer product_id, Integer card_id, Dish dish) {
        this.id = id;
        this.quantity = quantity;
        this.product_id = product_id;
        this.card_id = card_id;
        this.dish = dish;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getCard_id() {
        return card_id;
    }

    public void setCard_id(Integer card_id) {
        this.card_id = card_id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }
}
