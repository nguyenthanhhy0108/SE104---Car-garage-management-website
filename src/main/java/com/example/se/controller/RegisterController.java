package com.example.se.controller;
import com.example.se.model.userDetails;
import com.example.se.model.users;
import com.example.se.service.userDetailsService;
import com.example.se.service.usersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class RegisterController {
    private final userDetailsService UserDetailsService;
    private final usersService UsersService;
    @Autowired
    public RegisterController(userDetailsService userDetailsService, usersService usersService) {
        UserDetailsService = userDetailsService;
        UsersService = usersService;
    }

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, HttpServletRequest request){
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println(username);
        System.out.println(email);

        List<users> UsersList = UsersService.findByUsername(username);
        List<userDetails> UserDetailsList = UserDetailsService.findByEmail(email);

//        if(!UsersList.isEmpty() && !UserDetailsList.isEmpty()) {
//            model.addAttribute("username_is_used", "Phone number is already in use");
//            model.addAttribute("email_is_used", "Email is already in use");
//            return "register";
//        }
        if(!UsersList.isEmpty()) {
            model.addAttribute("username_is_used", "Phone number is already in use");
            return "register";
        }
        if(!UserDetailsList.isEmpty()) {
            model.addAttribute("email_is_used", "Email is already in use");
            return "register";
        }

        return "register";
    }
}
