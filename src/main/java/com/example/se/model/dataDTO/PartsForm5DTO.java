package com.example.se.model.dataDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PartsForm5DTO {
    private String partName;
    private int before;
    private int after;
    private int used;
}
