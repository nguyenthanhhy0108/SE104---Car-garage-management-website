package com.example.se.model;

import jakarta.persistence.*;
import lombok.Data;

//Present user_details table in database
@Data
@Entity
@Table(name = "USERDETAILS")
public class user_details {
    @Id
    @Column(name = "Username")
    private String username;

    @OneToOne
    @JoinColumn(name = "Username", insertable = false, updatable = false)
    private users users;

    @Column(name = "Email")
    private String email;

    @Column(name = "Name")
    private String name;

    @Column(name = "Nationality")
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
