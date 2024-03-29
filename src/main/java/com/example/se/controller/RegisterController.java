package com.example.se.controller;
import com.example.se.config.SecurityConfig;
import com.example.se.model.authorities;
import com.example.se.model.user_details;
import com.example.se.model.users;
import com.example.se.service.authoritiesService;
import com.example.se.service.user_detailsService;
import com.example.se.service.usersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class RegisterController {
    //Identify internal attribute
    private final user_detailsService user_detailsService;
    private final usersService UsersService;
    private final authoritiesService AuthoritiesService;
    private final PasswordEncoder encoder = SecurityConfig.passwordEncoder();

    //Initialize internal attribute
    @Autowired
    public RegisterController(user_detailsService user_detailsService, usersService usersService, authoritiesService authoritiesService) {
        this.user_detailsService = user_detailsService;
        this.UsersService = usersService;
        this.AuthoritiesService = authoritiesService;
    }

    //Redirect to register page
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    //Execute after submit register form
    //Get some parameter: username, password, email
    //Check space in parameters
    //Check username and email in database by Service layer method
    //  if they are overlapped: Response error message and redirect to register page
    //  else: Response successful message and eliminate some existed attribute in session and redirect to login page
    @PostMapping("/register")
    public String register(Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        List<users> UsersList = UsersService.findByUsername(username);
        List<user_details> userDetailsList = user_detailsService.findByEmail(email);

        model.addAttribute("username", username);
        model.addAttribute("email", email);

        if (!UsersList.isEmpty() && !userDetailsList.isEmpty()) {
            model.addAttribute("email_used", "Email are already used");
            model.addAttribute("username_used", "Phone number is already used");
            return "register";
        }
        if (!UsersList.isEmpty()) {
            model.addAttribute("username_used", "Phone number is already used");
            return "register";
        }
        if (!userDetailsList.isEmpty()) {
            model.addAttribute("email_used", "Email is already used");
            return "register";
        }

        users new_user = UsersService.save(new users(username, encoder.encode(password), 1));

        authorities authorities = AuthoritiesService.save(new authorities(username, "ROLE_USER"));

        user_details new_user_details = user_detailsService.save(new user_details(username, email, "", ""));

        HttpSession session = request.getSession();
        session.setAttribute("create_account_successfully", true);
        session.removeAttribute("password_wrong");
        session.removeAttribute("username_not_exist");
        return "login";
    }
}


