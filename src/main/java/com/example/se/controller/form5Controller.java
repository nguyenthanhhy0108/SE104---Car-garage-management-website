package com.example.se.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class form5Controller {
    
    @GetMapping("/form5")
    String getForm5Page() {
        return "form5";
    }
}
