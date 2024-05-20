package com.example.se.model.dataDTO;

import lombok.Data;

import java.util.List;

@Data
public class Form5InformationDTO
{
    private List<CarDTO> car;
    private List<OrderInDayDTO> order;
}
