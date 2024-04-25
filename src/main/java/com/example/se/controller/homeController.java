package com.example.se.controller;

import com.example.se.service.userDetailsService;
import com.example.se.service.usersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.Authenticator;

@Controller
public class homeController {

    private final userDetailsService userDetailsService;
    private final usersService userService;

    /**
     * Dependency Injection
     * @param userDetailsService: userDetailsService object
     * @param userService: usersService object
     */
    @Autowired
    public homeController(userDetailsService userDetailsService,
                          usersService userService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    /**
     * Redirect to home page
     * @return
     * home.html
     */
    @GetMapping("/home")
    public String homePage(HttpServletRequest request) {

        HttpSession httpSession = request.getSession();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getName().equals("anonymousUser")) {
            return "login";
        }
        else {
            httpSession.setAttribute("name", this.userDetailsService
                    .findByUsername(authentication.getName())
                    .get(0)
                    .getName());
        }
        return "home";
    }
}
