package com.zero.springsecurity.authentication.model;

public class JwtResponse {

    private boolean isSuccessful;
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
