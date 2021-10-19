package com.example.bartending_drink_app.evenbus;

public class LogOutEvent {
    private Boolean isLogOut;

    public LogOutEvent(Boolean isLogOut) {
        this.isLogOut = isLogOut;
    }

    public Boolean getLogOut() {
        return isLogOut;
    }

    public void setLogOut(Boolean logOut) {
        isLogOut = logOut;
    }
}
