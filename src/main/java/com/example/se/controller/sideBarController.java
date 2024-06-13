package com.example.se.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class sideBarController {
    @GetMapping("/home")
    String getFormHome() {
        return "home";
    }
    @GetMapping("/vehicle")
    String getFormVehicle() {
        return "vehicle";
    }
    @GetMapping("/report")
    String getFormReport() {
        return "report";
    }
        @GetMapping("/policy")
    String getFormPolicy() {
        return "policy";
    }
}
