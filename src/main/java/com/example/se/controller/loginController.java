package com.example.se.controller;

import com.example.se.service.authoritiesService;
import com.example.se.service.usersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {
    usersService service;
    authoritiesService authoritiesService;

    /**
     * Dependency Injection
     * @param service: usersService object
     * @param authoritiesService: authoritiesService object
     */
    @Autowired
    public loginController(usersService service, authoritiesService authoritiesService) {
        this.service = service;
        this.authoritiesService = authoritiesService;
    }

    /**
     * Redirect to login page
     * @return
     * login.html
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}
