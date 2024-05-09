package com.example.se.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "REPAIR_ORDERS_PARTS")
@IdClass(repairOrdersParts.class)
public class repairOrdersParts {

    @Id
    @Column(name = "PartID")
    private int partID;

    @Id
    @Column(name = "OrderNumber")
    private int orderNumber;

    @Column(name = "Quantity")
    private int quantity;
}
