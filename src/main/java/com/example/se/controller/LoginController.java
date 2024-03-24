package com.example.se.controller;

import com.example.se.model.authorities;
import com.example.se.model.users;
import com.example.se.service.authoritiesService;
import com.example.se.service.usersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    usersService service;
    authoritiesService authoritiesService;
    @Autowired
    public LoginController(usersService service, authoritiesService authoritiesService) {
        this.service = service;
        this.authoritiesService = authoritiesService;
    }

    //Redirect to login page
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    //Redirect ro home page
    @GetMapping("/home")
    public String homePage() {
//        List<users> users = service.findByUsername("0941609091");
//        System.out.println(service.findByUsername("1").get(0).getUsername());

//        System.out.println(users.get(0).getUserDetails().getEmail());

//        List<authorities> authorities = authoritiesService.findByUsername("0941609091");
//        System.out.println(users.get(0).getAuthorities().size());

//        System.out.println(service.findByUsername("1").get(0).getAuthorities().get(0).getAuthority());
//        System.out.println(users.get(0).getAuthorities().get(1).getAuthority());

//        System.out.println(authorities.get(0).getAuthority());
//        System.out.println(authorities.get(1).getAuthority());

//        for(int i = 0; i < users.get(0).getAuthorities().size(); i++){
//            System.out.println(i);
//            System.out.println(users.get(0).getAuthorities().get(i).getAuthority());
//        }

        users temp = service.findByUsername("1").get(0);
        service.delete(temp);
        return "home";
    }
}
