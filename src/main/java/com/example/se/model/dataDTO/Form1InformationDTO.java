package com.example.se.model.dataDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Form1InformationDTO {
    private int recordID;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String license;
    private String brand;
    private LocalDate date;

    /**
     * Constructor
     * @param recordID: int
     * @param name: String
     * @param phone: String
     * @param email: String
     * @param address: String
     * @param license: String
     * @param brand: String
     * @param date: String
     */
    public Form1InformationDTO(int recordID,
                               String name,
                               String phone,
                               String email,
                               String address,
                               String license,
                               String brand,
                               LocalDate date) {
        this.recordID = recordID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.license = license;
        this.brand = brand;
        this.date = date;
    }

    public Form1InformationDTO() {
    }
}
