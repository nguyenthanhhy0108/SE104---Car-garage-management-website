package com.example.se.model.ID;

import lombok.Data;

import java.io.Serializable;
@Data
public class authoritiesID implements Serializable {
    private String username;
    private String authority;

    public authoritiesID() {
    }

    /**
     * Constructor
     * @param username: String
     * @param authority: String
     */
    public authoritiesID(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }
}
