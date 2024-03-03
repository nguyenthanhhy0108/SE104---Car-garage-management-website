package com.example.se.controller;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, HttpServletRequest request){
        String username = (String)model.getAttribute("username");
        String email = (String)model.getAttribute("email");
        String password = (String)model.getAttribute("password");
        String re_password = (String)model.getAttribute("re-password");




        return "register";
    }
}
