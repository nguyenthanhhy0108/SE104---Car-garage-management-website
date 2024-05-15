package com.example.se.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.naming.Name;
import java.time.LocalDate;

@Data
@Entity(name = "REPAIR_ORDERS")
public class receipts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderNumber")
    private int ordernumber;

    @Column(name = "CarID")
    private int carId;

    @Column(name = "Date")
    private LocalDate date;

    @Column(name = "AmountOwed")
    private double amountOwed;

    @Column(name = "PaymentDate")
    private LocalDate paymentdate;

    @Column(name = "AmountPaid")
    private double amountpaid;

    @Column(name = "Note")
    private String note;

    @OneToOne
    @JoinColumn(name = "CarID", insertable = false, updatable = false)
    private cars cars;

    public receipts(int carId, LocalDate date, double amountOwed, LocalDate paymentdate, double amountpaid) {
        this.carId = carId;
        this.date = date;
        this.amountOwed = amountOwed;
        this.paymentdate = paymentdate;
        this.amountpaid = amountpaid;
    }

    public receipts() {

    }
}
