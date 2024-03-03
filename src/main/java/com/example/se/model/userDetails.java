package com.example.se.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class userDetails {
    @Id
    private String username;
    private String email;
    private String name;
    private String nationality;

    public userDetails() {
    }

    public userDetails(String username, String email, String name, String nationality) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.nationality = nationality;
    }
}
