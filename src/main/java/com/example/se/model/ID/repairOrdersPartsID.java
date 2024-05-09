package com.example.se.model.ID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class repairOrdersPartsID implements Serializable {
    private int OrderNumber;
    private int PartID;
}
