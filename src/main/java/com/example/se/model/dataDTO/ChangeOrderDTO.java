package com.example.se.model.dataDTO;

import lombok.Data;

@Data
public class ChangeOrderDTO {
    private int orderNumber;
    private String notes;
    private PartDTO part;
    private int quantity;
    private ServiceDTO service;
}
