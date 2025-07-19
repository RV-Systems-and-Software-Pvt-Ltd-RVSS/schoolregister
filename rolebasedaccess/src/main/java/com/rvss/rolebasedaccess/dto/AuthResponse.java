package com.rvss.rolebasedaccess.dto;

public class AuthResponse {

    private String message;
    private String token;
    private String role;

    // Constructor
    public AuthResponse(String message, String token, String role) {
        this.message = message;
        this.token = token;
        this.role = role;
    }

    // Getters
    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }

    // Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String message;
        private String token;
        private String role;

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public AuthResponse build() {
            return new AuthResponse(message, token, role);
        }
    }
}
