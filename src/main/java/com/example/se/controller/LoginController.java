package com.example.se.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

@Controller
public class LoginController {
    //Redirect to login page
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    //Redirect ro home page
    @GetMapping("/home")
    public String homePage() {
        return "home";
    }
}
