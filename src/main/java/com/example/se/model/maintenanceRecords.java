package com.example.se.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "MAINTENANCE_RECORDS")
public class maintenanceRecords {

    @Id
    @Column(name = "RecordID")
    private int recordID;

    @Column(name = "CarID")
    private int carID;

    @Column(name = "DateReceived")
    private String dateReceived;

    @OneToOne
    @JoinColumn(name = "CarID", insertable = false, updatable = false)
    private cars cars;

    public maintenanceRecords(int recordID,
                              int carID,
                              String dateReceived) {
        this.recordID = recordID;
        this.carID = carID;
        this.dateReceived = dateReceived;
    }

    public maintenanceRecords() {
    }
}
