package com.example.se.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class form6Controller {
    
    @GetMapping("/form6")
    String getForm5Page() {
        return "form6";
    }
}
