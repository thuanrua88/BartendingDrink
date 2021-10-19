package com.example.bartending_drink_app.evenbus;

public class AddItemToCardEvent {
    private Boolean isRefresh;

    public AddItemToCardEvent(Boolean isRefresh) {
        this.isRefresh = isRefresh;
    }

    public Boolean getIsRefresh() {
        return isRefresh;
    }

    public void setIsRefresh(Boolean isRefresh) {
        isRefresh = isRefresh;
    }
}
