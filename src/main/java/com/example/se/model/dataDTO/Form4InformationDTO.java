package com.example.se.model.dataDTO;

import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
public class Form4InformationDTO
{
    private int orderId;
    private String name;
    private String license;
    private String phone;
    private String email;
    private LocalDate paydate;
    private double amountpaid;

    /**
     * Constructor
     * @param orderId
     * @param name
     * @param license
     * @param phone
     * @param email
     * @param paydate
     * @param amountpaid
     */
    public Form4InformationDTO(int orderId, String name, String license, String phone, String email, LocalDate paydate,double amountpaid)
    {
        this.orderId=orderId;
        this.name=name;
        this.license=license;
        this.phone=phone;
        this.email=email;
        this.paydate=paydate;
        this.amountpaid=amountpaid;
    }
    public Form4InformationDTO(){}
}
