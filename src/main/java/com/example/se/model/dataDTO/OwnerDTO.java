package com.example.se.model.dataDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDTO{
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private String oldPhoneNumber;
}
