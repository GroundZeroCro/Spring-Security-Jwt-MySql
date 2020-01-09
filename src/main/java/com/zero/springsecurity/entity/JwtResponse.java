package com.zero.springsecurity.entity;

public class JwtResponse {

    private static final long serialVersionUID = -8091879091924046844L;

    private String token;

    public JwtResponse() {
    }

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
