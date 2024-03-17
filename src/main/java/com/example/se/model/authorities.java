package com.example.se.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


//Present authorities table in database
@Entity
@Data
@Table(name = "AUTHORITIES")
public class authorities {
    @Id
    @Column(name = "Username")
    private String username;
    @Column(name = "Authority")
    private String authority;

    public authorities() {
    }

    public authorities(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }
}
