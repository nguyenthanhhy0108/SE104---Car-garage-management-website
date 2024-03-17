package com.example.se.model;

import jakarta.persistence.*;
import lombok.Data;

//Present users table in database
@Data
@Entity
@Table(name = "USERS")
public class users {
    @Id
    @Column(name = "Username")
    private String username;
    @Column(name = "Password")
    private String password;
    @Column(name = "Enabled")
    private int enabled;

    public users() {
    }

    public users(String username, String password, int enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }
}
