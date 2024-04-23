package com.example.integradora.Response;

import com.example.integradora.TokenData;

public class LoginResponse {
    private String title;
    private String message;
    private String type;
    private TokenData data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TokenData getData() {
        return data;
    }

    public void setData(TokenData data) {
        this.data = data;
    }
}