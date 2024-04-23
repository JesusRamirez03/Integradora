package com.example.integradora;

public class TokenData {
    private Token token;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public static class Token {
        private String type;
        private String token;
        private String expires_at;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getExpiresAt() {
            return expires_at;
        }

        public void setExpiresAt(String expires_at) {
            this.expires_at = expires_at;
        }
    }
}