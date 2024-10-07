package com.example.webshop.DTOS;

import com.example.webshop.model.Role;
import lombok.Getter;

import java.io.Serializable;

public class UserDTO implements Serializable {
    // Getters och Setters
    @Getter
    private int id;
    @Getter
    private String username;
    private String password;
    @Getter
    private Role role;

    public UserDTO() {
    }

    public UserDTO(int id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
