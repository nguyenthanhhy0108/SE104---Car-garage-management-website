package com.example.se.model.dataDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Form5_1DTO {
    private double total;
    private List<BrandForm5_1DTO> list;
}
