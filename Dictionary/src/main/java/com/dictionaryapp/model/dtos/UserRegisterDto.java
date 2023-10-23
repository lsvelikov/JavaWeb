package com.dictionaryapp.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegisterDto {

    @NotNull
    @Size(min = 3, max = 20, message = "Username length muss be between 3 and 20 characters")
    private String username;
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Enter a valid email")
    private String email;
    @NotNull
    @Size(min = 3, max = 20, message = "Password length muss be between 3 and 20 characters")
    private String password;
    @NotNull
    private String confirmPassword;

    public UserRegisterDto() {
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterDto setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
