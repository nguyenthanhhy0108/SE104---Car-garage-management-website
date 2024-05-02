package com.example.se.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.naming.Name;
import java.time.LocalDate;

@Data
@Entity(name = "REPAIR_ORDERS")
public class receipts {
    @Id
    @Column(name = "OrderNumber")
    private int ordernumber;

    @Column(name = "CarID")
    private int carId;

    @Column(name = "OwnerID")
    private int ownerId;

    @Column(name = "PaymentDate")
    private LocalDate paymentdate;

    @Column(name = "AmountPaid")
    private double amountpaid;

    @OneToOne
    @JoinColumn(name = "CarID", insertable = false, updatable = false)
    private cars cars;

    @OneToOne
    @JoinColumn(name = "OwnerID", insertable = false,updatable = false)
    private owners owners;

    public receipts(int ordernumber,int carId,int ownerId,LocalDate paymentdate,double amountpaid)
    {
        this.ordernumber=ordernumber;
        this.carId=carId;
        this.ownerId=ownerId;
        this.paymentdate=paymentdate;
        this.amountpaid=amountpaid;
    }

    public receipts() {

    }
}
