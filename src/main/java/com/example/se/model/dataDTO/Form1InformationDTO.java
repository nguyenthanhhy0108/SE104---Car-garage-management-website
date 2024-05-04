package com.example.se.model.dataDTO;

import lombok.Data;

import java.util.List;

@Data
public class Form1InformationDTO {
    private String name;
    private String phone;
    private String email;
    private String address;
    private List<CarDTO> cars;

    public Form1InformationDTO(String name,
                               String phone,
                               String email,
                               String address,
                               List<CarDTO> cars) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.cars = cars;
    }

    public Form1InformationDTO() {
    }
}
