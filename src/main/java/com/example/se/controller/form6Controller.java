package com.example.se.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class form6Controller {
    
    @GetMapping("/change_policies")
    String getForm5Page() {
        return "change_policies";
    }
}
