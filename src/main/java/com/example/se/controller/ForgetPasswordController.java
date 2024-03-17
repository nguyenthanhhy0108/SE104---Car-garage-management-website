package com.example.se.controller;

import com.example.se.config.SecurityConfig;
import com.example.se.model.users;
import com.example.se.model.verification_email_structure;
import com.example.se.service.emailSenderService;
import com.example.se.service.user_detailsService;
import com.example.se.service.usersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    //2 Internal attributes for getting user information
    private final user_detailsService userDetailsService;
    private final usersService usersService;
    //Internal attribute for mail content structure
    private verification_email_structure verificationEmailStructure = new verification_email_structure();
    //Internal attributes for password retrieval and change
    private final PasswordEncoder encoder = SecurityConfig.passwordEncoder();
    private String username;

    //Initialize internal attribute
    @Autowired
    public ForgetPasswordController(com.example.se.service.emailSenderService emailSenderService, user_detailsService userDetailsService, usersService usersService) {
        this.emailSenderService = emailSenderService;
        this.userDetailsService = userDetailsService;
        this.usersService = usersService;
    }

    //Redirect to forget password page
    @GetMapping("/password")
    public String passwordPage(){return "password";}

    //Process after submit former form
    @PostMapping("/password")
    /*
    Input: Request from client
    Output: Response entity contain response body and http status
    Output form1 contains: - "Fail" - Boolean
                           - "notExist" - String
                           - "notMatch" - String
                           - "email" - String
                           - "username" - String

    Output form2 contains: - "Fail" - Boolean
                           - "expiredCode" - String
                           - "wrongCode" - String

    Output form1 contains: - "Fail" - Boolean
                           - "overlapped" - String
                           - "successful" - String
    */
    public ResponseEntity<Map<String, Object>> process(@RequestParam("formId") String formId, HttpServletRequest request, HttpServletResponse response, Model model){
        Map<String, Object> resposeMap = new HashMap<>();
        if ("form1".equals(formId)) {
            this.verificationEmailStructure = new verification_email_structure();
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
                this.verificationEmailStructure.setVerification_code(emailSenderService.randomVerificationCode());
                this.verificationEmailStructure.replace_code();
                this.verificationEmailStructure.setSent_time(LocalDateTime.now());
                emailSenderService.sendEmail(email_from_client, this.verificationEmailStructure);
            }
            //Return some attribute

            HttpSession session = request.getSession();
            session.setAttribute("username", username_from_client);
            session.setAttribute("email", email_from_client);

            resposeMap.put("email", email_from_client);
            resposeMap.put("username", username_from_client);

            response.setContentType("text/html");

            this.username = username_from_client;

            return new ResponseEntity<>(resposeMap, HttpStatus.OK);
        }
        if("form2".equals(formId)){
            boolean fail = false;

            //Get code from user typing
            String codeFromClient = request.getParameter("char1")
                    + request.getParameter("char2")
                    + request.getParameter("char3")
                    + request.getParameter("char4");

            //Get current time
            LocalDateTime now = LocalDateTime.now();
            //Check expired code and response
            if(now.isAfter(this.verificationEmailStructure.getSent_time().plusMinutes(30))){
                fail = true;
                resposeMap.put("expiredCode", "Your provided code was expired");
                resposeMap.put("Fail", true);
                return new ResponseEntity<>(resposeMap, HttpStatus.OK);
            }
            else{
                //Check code (true, false)
                if(!codeFromClient.equals(this.verificationEmailStructure.getVerification_code())){
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
        if("form3".equals(formId)){
            //Find old password from database by username
            String old_pass = usersService.findByUsername(this.username).get(0).getPassword();
            //Get new password from client
            String new_pass = request.getParameter("password");
            //Check overlap
            if(encoder.matches(new_pass, old_pass)){
                resposeMap.put("Fail", true);
                resposeMap.put("overlapped", "New password must not overlap with current password");
                return new ResponseEntity<>(resposeMap, HttpStatus.OK);
            }
            else {
                //Set new pass into database
                users new_users = usersService.updatePasswordByUsername(this.username, encoder.encode(new_pass));
                resposeMap.put("successful", "Change password successfully");
                resposeMap.put("Fail", false);
                return new ResponseEntity<>(resposeMap, HttpStatus.OK);
            }
        }
        return null;
    }
}
