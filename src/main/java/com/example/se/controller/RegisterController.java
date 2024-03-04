package com.example.se.controller;
import com.example.se.config.SecurityConfig;
import com.example.se.model.authorities;
import com.example.se.model.user_details;
import com.example.se.model.users;
import com.example.se.service.authoritiesService;
import com.example.se.service.user_detailsService;
import com.example.se.service.usersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class RegisterController {
    private final user_detailsService user_detailsService;
    private final usersService UsersService;
    private final authoritiesService AuthoritiesService;
    private final PasswordEncoder encoder = SecurityConfig.passwordEncoder();
    @Autowired
    public RegisterController(user_detailsService user_detailsService, usersService usersService, authoritiesService authoritiesService) {
        this.user_detailsService = user_detailsService;
        this.UsersService = usersService;
        this.AuthoritiesService = authoritiesService;
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

        List<users> UsersList = UsersService.findByUsername(username);
        List<user_details> userDetailsList = user_detailsService.findByEmail(email);

        model.addAttribute("username", username);
        model.addAttribute("email", email);

        if(!UsersList.isEmpty() && !userDetailsList.isEmpty()) {
            model.addAttribute("username_is_used", "Phone number is already in use");
            model.addAttribute("email_is_used", "Email is already in use");
            return "register";
        }
        if(!UsersList.isEmpty()) {
            model.addAttribute("username_is_used", "Phone number is already in use");
            return "register";
        }
        if(!userDetailsList.isEmpty()) {
            model.addAttribute("email_is_used", "Email is already in use");
            return "register";
        }

        UsersService.save(new users(username, encoder.encode(password), 1));
        AuthoritiesService.save(new authorities(username, "ROLE_USER"));
        user_detailsService.save(new user_details(username, email, "", ""));

        model.addAttribute("create_account_successfully", true);

        return "login";
    }
}
