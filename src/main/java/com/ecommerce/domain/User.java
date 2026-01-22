package com.ecommerce.domain;

public class User {

    private final String username;
    private final Role role;

    public User(String username, Role role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    public boolean isAdmin() {
        return role == Role.ADMIN;
    }

    public String getName() {
        return "";
    }
}
