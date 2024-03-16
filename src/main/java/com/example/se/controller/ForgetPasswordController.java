package com.example.se.controller;

import com.example.se.model.verification_email_structure;
import com.example.se.service.emailSenderService;
import com.example.se.service.user_detailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ForgetPasswordController {
    //Internal attribute for sending mail
    private final emailSenderService emailSenderService;
    private final user_detailsService userDetailsService;
    //Internal attribute for mail content structure
    private verification_email_structure verificationEmailStructure = new verification_email_structure();

    //Initialize internal attribute
    @Autowired
    public ForgetPasswordController(com.example.se.service.emailSenderService emailSenderService, user_detailsService userDetailsService) {
        this.emailSenderService = emailSenderService;
        this.userDetailsService = userDetailsService;
    }

    //Redirect to forget password page
    @GetMapping("/password")
    public String passwordPage(){return "password";}

    //Process after submit former form
    @PostMapping("/password")
    /*
    Input: Request from client
    Output: Response entity contain response body and http status
    Output contains: - "Fail" - Boolean
                     - "notExist" - String
                     - "notMatch" - String
                     - "email" - String
                     - "username" - String
    */
    public ResponseEntity<Map<String, Object>> process(@RequestParam("formId") String formId, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> resposeMap = new HashMap<>();
        if ("form1".equals(formId)) {
            boolean fail = false;
            resposeMap.put("Fail", false);
            resposeMap.put("notExist", "");
            resposeMap.put("notMatch", "");
            //Get parameter received email from client
            String email_from_client = request.getParameter("email");
            String username_from_client = request.getParameter("username");

            //Check username exist
            if(userDetailsService.findByUsername(username_from_client).isEmpty()){
                resposeMap.put("notExist", "Phone number does not exist.");
                resposeMap.put("Fail", true);
                fail = true;
            }
            else{
                //Check username match with email received
                if(!userDetailsService.findByUsername(username_from_client)
                        .get(0)
                        .getEmail()
                        .equals(email_from_client)){
                    resposeMap.put("notMatch", "Phone number and email do not match");
                    resposeMap.put("Fail", true);
                    fail = true;
                }
            }
            if(!fail){
                //Create a verification code and put it into mail message
                //Record time sending mail
                //Send mail
                verificationEmailStructure.setVerification_code(emailSenderService.randomVerificationCode());
                verificationEmailStructure.replace_code();
                verificationEmailStructure.setSent_time(LocalDateTime.now());
                emailSenderService.sendEmail(email_from_client, verificationEmailStructure);
            }

            //Return some attribute, unfinished to be continue...

            resposeMap.put("email", email_from_client);
            resposeMap.put("username", username_from_client);

            response.setContentType("text/html");

            return new ResponseEntity<>(resposeMap, HttpStatus.OK);
        }
        if("form2".equals(formId)){
            boolean fail = false;
            String codeFromClient = request.getParameter("char1")
                    + request.getParameter("char2")
                    + request.getParameter("char3")
                    + request.getParameter("char4");

            LocalDateTime now = LocalDateTime.now();
            if(now.isAfter(verificationEmailStructure.getSent_time().plusMinutes(1))){
                fail = true;
                resposeMap.put("expiredCode", "Your provided code was expired");
                resposeMap.put("Fail", true);
                return new ResponseEntity<>(resposeMap, HttpStatus.OK);
            }
            else{
                if(!codeFromClient.equals(verificationEmailStructure.getVerification_code())){
                    fail = true;
                    resposeMap.put("wrongCode", "Your typed code was wrong");
                    resposeMap.put("Fail", true);
                    return new ResponseEntity<>(resposeMap, HttpStatus.OK);
                }
                else {
                    resposeMap.put("Fail", false);
                    return new ResponseEntity<>(resposeMap, HttpStatus.OK);
                }
            }
        }
        return null;
    }
}
