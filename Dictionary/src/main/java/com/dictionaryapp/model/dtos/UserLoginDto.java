package com.dictionaryapp.model.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserLoginDto {

    @NotNull
    @Size(min = 3, max = 20, message = "Username length muss be between 3 and 20 characters")
    private String username;
    @NotNull
    @Size(min = 3, max = 20, message = "Password length muss be between 3 and 20 characters")
    private String password;

    public UserLoginDto() {
    }

    public String getUsername() {
        return username;
    }

    public UserLoginDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
