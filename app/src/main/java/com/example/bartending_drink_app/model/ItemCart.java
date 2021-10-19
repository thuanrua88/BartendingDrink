package com.example.bartending_drink_app.model;

public class ItemCart {
    private Integer id;
    private Integer quantity;
    private Integer card_id;
    private Integer product_id;
    private Dish dish;

    public ItemCart(Integer id, Integer quantity, Integer card_id, Integer product_id, Dish dish) {
        this.id = id;
        this.quantity = quantity;
        this.card_id = card_id;
        this.product_id = product_id;
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

    public Integer getCard_id() {
        return card_id;
    }

    public void setCard_id(Integer card_id) {
        this.card_id = card_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }
}
