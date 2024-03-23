package com.example.se.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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

    @OneToOne(mappedBy = "users", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private user_details userDetails;

    @OneToMany(mappedBy = "users", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private List<authorities> authorities;

    public users() {
    }

    public users(String username, String password, int enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public void add(authorities authorities){
        if(this.authorities == null){
            this.authorities = new ArrayList<>();
        }
        authorities.setUsers(this);
        this.authorities.add(authorities);
    }
}
