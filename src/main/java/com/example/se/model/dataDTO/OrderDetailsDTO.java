package com.example.se.model.dataDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDTO {
    private int OrderNumber;
    private PartDTO part;
    private int quantity;
    private ServiceDTO service;
    private double total;
}
