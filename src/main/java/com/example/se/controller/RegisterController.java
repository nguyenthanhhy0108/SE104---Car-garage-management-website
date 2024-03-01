package com.example.se.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {
    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

//    @PostMapping("/register")
//    public ResponseEntity<?>
}
