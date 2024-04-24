package com.example.se.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "OWNERS")
public class owners {

    @Id
    @Column(name = "OwnerID")
    private int ownerID;

    @Column(name = "OwnerName")
    private String ownerName;

    @Column(name = "OwnerAddress")
    private String ownerAddress;

    @Column(name = "OwnerPhoneNumber")
    private String ownerPhoneNumber;

    @Column(name = "OwnerEmail")
    private String ownerEmail;

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

    @OneToMany(mappedBy = "owners", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private List<cars> carsList;

    /**
     * Constructor
     * @param ownerID int
     * @param ownerName String
     * @param ownerAddress String
     * @param ownerPhoneNumber String
     * @param ownerEmail String
     * @param username String
     */
    public owners(int ownerID,
                  String ownerName,
                  String ownerAddress,
                  String ownerPhoneNumber,
                  String ownerEmail,
                  String username) {
        this.ownerID = ownerID;
        this.ownerName = ownerName;
        this.ownerAddress = ownerAddress;
        this.ownerPhoneNumber = ownerPhoneNumber;
        this.ownerEmail = ownerEmail;
        this.username = username;
    }

    /**
     * Constructor
     */
    public owners() {
    }
}
