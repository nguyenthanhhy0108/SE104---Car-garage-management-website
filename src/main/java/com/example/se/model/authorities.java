package com.example.se.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class authorities {
    @Id
    private String username;
    private String authority;

    public authorities() {
    }

    public authorities(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }
}
