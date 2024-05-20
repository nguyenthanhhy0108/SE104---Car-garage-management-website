package com.example.se.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class vehicleController {

    @GetMapping("/vehicle")
    String getVehiclePage() {
        return "vehicle";
    }
}
