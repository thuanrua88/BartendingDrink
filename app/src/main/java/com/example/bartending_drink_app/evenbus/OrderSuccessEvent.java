package com.example.bartending_drink_app.evenbus;

public class OrderSuccessEvent {
    private Boolean isSuccess;

    public OrderSuccessEvent(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }
}
