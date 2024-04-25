package com.example.se.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity(name = "MAINTENANCE_RECORDS")
public class maintenanceRecords {

    @Id
    @Column(name = "RecordID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recordID;

    @Column(name = "CarID")
    private int carID;

    @Column(name = "DateReceived")
    private LocalDate dateReceived;

    @OneToOne
    @JoinColumn(name = "CarID", insertable = false, updatable = false)
    private cars cars;

    /**
     * Constructor full argument
     * @param recordID: int
     * @param carID: int
     * @param dateReceived: String (date)
     */
    public maintenanceRecords(int recordID,
                              int carID,
                              LocalDate dateReceived) {
        this.recordID = recordID;
        this.carID = carID;
        this.dateReceived = dateReceived;
    }

    public maintenanceRecords() {
    }
}
