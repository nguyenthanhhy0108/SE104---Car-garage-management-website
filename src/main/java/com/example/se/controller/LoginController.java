package com.example.se.controller;

import com.example.se.model.users;
import com.example.se.service.usersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginController {
    usersService service;
    @Autowired
    public LoginController(usersService service) {
        this.service = service;
    }

    //Redirect to login page
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    //Redirect ro home page
    @GetMapping("/home")
    public String homePage() {
        List<users> users = service.findByUsername("0941609091");

        System.out.println(users.get(0).getUserDetails().getEmail());
        System.out.println(users.get(0).getAuthorities().get(0).getAuthority());
        return "home";
    }
}
