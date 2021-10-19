package com.example.bartending_drink_app.model;

public class LoginResponse {
    private String username;
    private String email;
    private String jwt;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
//    public Integer getUser_id() {
//        if (this.user_id == null){
//            return 0;
//        }
//        return user_id;
//    }

//    public void setUser_id(Integer user_id) {
//        this.user_id = user_id;
//    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
