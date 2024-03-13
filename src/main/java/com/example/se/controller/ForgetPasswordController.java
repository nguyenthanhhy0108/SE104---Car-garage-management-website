package com.example.se.controller;

import com.example.se.model.verification_email_structure;
import com.example.se.service.emailSenderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ForgetPasswordController {
    //Internal attribute for sending mail
    private final emailSenderService emailSenderService;
    //Internal attribute for mail content structure
    private verification_email_structure verificationEmailStructure = new verification_email_structure();

    //Initialize internal attribute
    @Autowired
    public ForgetPasswordController(com.example.se.service.emailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    //Redirect to forget password page
    @GetMapping("/password")
    public String passwordPage(){return "password";}

    //Process after submit former form
    @PostMapping("/password")
    /*
    Input: Request from client
    Output: Response entity contain response body and http status
    */
    public ResponseEntity<Map<String, Boolean>> process(HttpServletRequest request){
        //Get parameter received email from client
        String receive_email = request.getParameter("email");

        //Create a verification code and put it into mail message
        //Record time sending mail
        //Send mail
        verificationEmailStructure.setVerification_code(emailSenderService.randomVerificationCode());
        verificationEmailStructure.replace_code();
        verificationEmailStructure.setSent_time(LocalDateTime.now());
        emailSenderService.sendEmail(receive_email, verificationEmailStructure);

        //Return some attribute, unfinished to be continue...
        Map<String, Boolean> resposeMap = new HashMap<>();
        resposeMap.put("email", true);
        resposeMap.put("username", true);
        return new ResponseEntity<>(resposeMap, HttpStatus.OK);
    }
}
