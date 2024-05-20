package com.example.se.controller;

import com.example.se.model.dataDTO.BrandIncomeDTO;
import com.example.se.model.dataDTO.Form5InformationDTO;
import com.example.se.service.receiptsService;
import com.example.se.service.ownersService;
import com.example.se.service.carsService;
import com.example.se.service.partsService;
import com.example.se.service.repairOrdersPartsServices;
import com.example.se.service.repairOrderServicesService;
import com.example.se.service.servicesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class recordController {
    private final receiptsService receiptsService;
    private final ownersService ownersService;
    private final carsService carsService;
    private final partsService partsService;
    private final repairOrdersPartsServices repairOrdersPartsService;
    private final repairOrderServicesService repairOrderServicesService;
    private final servicesServices servicesServices;

    @Autowired
    public recordController(receiptsService receiptsService, ownersService ownersService, carsService carsService, partsService partsService, repairOrdersPartsServices repairOrdersPartsService, repairOrderServicesService repairOrderServicesService, servicesServices servicesServices) {
        this.receiptsService = receiptsService;
        this.ownersService = ownersService;
        this.carsService = carsService;
        this.partsService = partsService;
        this.repairOrdersPartsService = repairOrdersPartsService;
        this.repairOrderServicesService = repairOrderServicesService;
        this.servicesServices = servicesServices;
    }

    @GetMapping("/get-brands-records")
    public ResponseEntity<Map<String, Object>> getBrandRecords(@RequestParam int month) {
        List<BrandIncomeDTO> brandIncomes = receiptsService.findTotalIncomeByBrandForMonth(month);

        double totalRevenue = brandIncomes.stream().mapToDouble(BrandIncomeDTO::getTotalIncome).sum();

        List<Map<String, Object>> brandInfo = brandIncomes.stream().map(brandIncome -> {
            Map<String, Object> brandData = new HashMap<>();
            brandData.put("ID", brandIncome.getBrand().hashCode());
            brandData.put("Vehicle brand", brandIncome.getBrand());
            brandData.put("Revenue", brandIncome.getTotalIncome());
            double percentage = (brandIncome.getTotalIncome() / totalRevenue) * 100;
            brandData.put("Percentage", String.format("%.2f%%", percentage));
            return brandData;
        }).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("month", month);
        response.put("totalRevenue", totalRevenue);
        response.put("brandRecords", brandInfo);

        return ResponseEntity.ok(response);
    }
}