package com.example.integradora.Models;

public class LoginM {
    private String email;
    private String password;

    public LoginM(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
