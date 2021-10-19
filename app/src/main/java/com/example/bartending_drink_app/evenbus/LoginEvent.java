package com.example.bartending_drink_app.evenbus;

public class LoginEvent {
    private Boolean isLoginSuccess;

    public LoginEvent(Boolean isLoginSuccess) {
        this.isLoginSuccess = isLoginSuccess;
    }

    public Boolean getLoginSuccess() {
        return isLoginSuccess;
    }

    public void setLoginSuccess(Boolean loginSuccess) {
        isLoginSuccess = loginSuccess;
    }
}
