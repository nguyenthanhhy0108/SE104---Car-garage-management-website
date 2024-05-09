package com.example.se.model.dataDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Form2InformationDTO {
    private String licenseNumber;
    private List<OrderInDayDTO> Dates;
}
