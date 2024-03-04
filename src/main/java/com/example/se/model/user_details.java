package com.example.se.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
public class user_details {
    @Id
    private String username;
    private String email;
    private String name;
    private String nationality;

    public user_details() {
    }

    public user_details(String username, String email, String name, String nationality) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.nationality = nationality;
    }
}
