package com.example.se.controller;
import com.example.se.service.receiptsService;
import org.springframework.stereotype.Controller;
import com.example.se.model.brands;
import com.example.se.model.cars;
import com.example.se.model.dataDTO.Form1InformationDTO;
import com.example.se.model.maintenanceRecords;
import com.example.se.model.owners;
import com.example.se.service.maintenanceRecordsService;
import com.example.se.service.brandsService;
import com.example.se.service.carsService;
import com.example.se.service.ownersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.*;
@Controller
public class receiptsController {
    private final receiptsService receiptsService;
    private  final ownersService ownersService;
    private  final carsService carsService;

    @Autowired
    public receiptsController(receiptsService receiptsService, com.example.se.service.ownersService ownersService, com.example.se.service.carsService carsService) {
        this.receiptsService = receiptsService;
        this.ownersService = ownersService;
        this.carsService = carsService;
    }

}
