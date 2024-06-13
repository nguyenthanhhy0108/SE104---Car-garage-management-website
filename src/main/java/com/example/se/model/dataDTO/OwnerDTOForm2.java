package com.example.se.model.dataDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OwnerDTOForm2 extends OwnerDTO {
    private String oldPhoneNumber;
}
