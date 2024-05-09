package com.example.se.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@IdClass(repairOrderServices.class)
@Entity(name = "REPAIR_ORDER_SERVICES")
@AllArgsConstructor
@NoArgsConstructor
public class repairOrderServices {

    @Id
    @Column(name = "OrderNumber")
    private int orderNumber;

    @Id
    @Column(name = "ServiceID")
    private int serviceID;
}
