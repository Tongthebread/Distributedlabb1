package com.example.webshop.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
public class User {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private Role role;
    public User(int id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User() {
        this.role = Role.CUSTOMER;
    }
}
