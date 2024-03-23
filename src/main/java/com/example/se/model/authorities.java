package com.example.se.model;

import com.example.se.model.ID.authoritiesID;
import jakarta.persistence.*;
import lombok.Data;


//Present authorities table in database
@Entity
@Data
@Table(name = "AUTHORITIES")
@IdClass(authoritiesID.class)
public class authorities {
    @Id
    @Column(name = "Username")
    private String username;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "Username", insertable = false, updatable = false)
    private users users;

    @Id
    @Column(name = "Authority")
    private String authority;

    public authorities() {
    }

    public authorities(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }
}
