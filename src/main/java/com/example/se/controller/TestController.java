package com.example.se.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.processing.Generated;
import java.util.Map;

@Controller
public class TestController {
    @GetMapping("/test")
    public String testPage(){
        return "test";
    }

    @PostMapping("/process")
    public ResponseEntity<String> process(HttpServletRequest request) {
        String username = request.getParameter("username");
        return new ResponseEntity<>(username, HttpStatus.OK);
    }
}

