package com.challenge.application.model;

import com.challenge.application.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private User user;
    private String token;
    private Error error;

    public UserResponse(User user, String token, Error e) {
        this.user = user;
        this.token = token;
        this.error = e;
    }

    public UserResponse(Error e) {
        this.error = e;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error e) {
        this.error = e;
    }
}
