package com.example.se.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "CARS")
public class cars {

    @Id
    @Column(name = "CarID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carID;

    @Column(name = "LicensePlate")
    private String licensePlate;

    @Column(name = "OwnerID")
    private int ownerID;

    @Column(name = "BrandID")
    private int brandID;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "BrandID", insertable = false, updatable = false)
    private brands brands;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "OwnerID", insertable = false, updatable = false)
    private owners owners;

    @OneToOne(mappedBy = "cars", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.REMOVE
    })
    private maintenanceRecords  maintenanceRecords;

    /**
     * Constructor
     * @param carID: int
     * @param licensePlate: String
     * @param ownerID: int
     * @param brandID: int
     */
    public cars(int carID,
                String licensePlate,
                int ownerID,
                int brandID) {
        this.carID = carID;
        this.licensePlate = licensePlate;
        this.ownerID = ownerID;
        this.brandID = brandID;
    }

    /**
     * Constructor
     */
    public cars() {
    }
}
