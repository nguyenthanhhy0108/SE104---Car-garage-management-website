package com.example.se.model.dataDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Form3InformationDTO {
    private int ID;
    private List<CarAndDebtDTO> Cars;
    private String Name;
}
