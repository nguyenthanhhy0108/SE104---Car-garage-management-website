package com.example.se.model.dataDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BrandIncomeDTO {
    private String brand;
    private Double totalIncome;
}
