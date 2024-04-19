package com.example.se.controller;

import com.example.se.config.securityConfig;
import com.example.se.model.users;
import com.example.se.model.verificationEmailStructure;
import com.example.se.service.emailService;
import com.example.se.service.userDetailsService;
import com.example.se.service.usersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class forgetPasswordController {
    //Internal attribute for sending mail
    private final emailService emailService;
    //2 Internal attributes for getting user information
    private final userDetailsService userDetailsService;
    private final usersService usersService;
    //Internal attribute for mail content structure
    private verificationEmailStructure verificationEmailStructure = new verificationEmailStructure();
    //Internal attributes for password retrieval and change
    private final PasswordEncoder encoder = securityConfig.passwordEncoder();
    private String username;

    /**
     * Dependency Injection
     * @param emailService: emailSenderService object
     * @param userDetailsService: user_detailsService object
     * @param usersService: usersService object
     */
    @Autowired
    public forgetPasswordController(emailService emailService,
                                    userDetailsService userDetailsService,
                                    usersService usersService) {
        this.emailService = emailService;
        this.userDetailsService = userDetailsService;
        this.usersService = usersService;
    }

    /**
     * Redirect to forget password page
     * @return
     * password.html
     */
    @GetMapping("/password")
    public String passwordPage(){return "password";}

    /**
     * Process after submit forgot password form
     * @param formId: RequestParam
     * @param request: HttpServletRequest
     * @param response: HttpServletResponse
     * @param model: Model for interacting with UI
     * @return
     * ResponseEntity
     */
    @PostMapping("/password")
    public ResponseEntity<Map<String, Object>> process(@RequestParam("formId") String formId,
                                                       HttpServletRequest request,
                                                       HttpServletResponse response,
                                                       Model model){
        /*
        Input: Request from client
        Output: Response entity contain response body and http status
        Output form1 contains: - "Fail" - Boolean
                               - "notExist" - Boolean
                               - "notMatch" - Boolean
                               - "email" - String
                               - "username" - String

        Output form2 contains: - "Fail" - Boolean
                               - "expiredCode" - String
                               - "wrongCode" - String

        Output form1 contains: - "Fail" - Boolean
                               - "overlapped" - String
                               - "successful" - String
        */
        Map<String, Object> resposeMap = new HashMap<>();
        if ("form1".equals(formId)) {
            this.verificationEmailStructure = new verificationEmailStructure();
            boolean fail = false;
            resposeMap.put("Fail", false);
            resposeMap.put("notExist", false);
            resposeMap.put("notMatch", false);
            //Get parameter received email from client
            String email_from_client = request.getParameter("email");
            String username_from_client = request.getParameter("username");

            //Check username exist
            if(!usersService.checkUsernameExist(username_from_client)){
                resposeMap.put("notExist", true);
                resposeMap.put("Fail", true);
                fail = true;
            }
            else{
                //Check username match with email received
                if(!userDetailsService.checkEmailMatchUsername(email_from_client, username_from_client)){
                    resposeMap.put("notMatch", true);
                    resposeMap.put("Fail", true);
                    fail = true;
                }
            }
            if(!fail){
                //Create a verification code and put it into mail message
                //Record time sending mail
                //Send mail
                emailService.prepareMail(this.verificationEmailStructure);
                emailService.sendEmail(email_from_client, this.verificationEmailStructure);
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
            resposeMap.put("Fail", false);
            resposeMap.put("expiredCode", false);
            resposeMap.put("wrongCode", false);
            //Get code from user typing
            String codeFromClient = request.getParameter("char1")
                    + request.getParameter("char2")
                    + request.getParameter("char3")
                    + request.getParameter("char4");
            //Check expired code and response
            if(emailService.checkVerificationCodeExpired(this.verificationEmailStructure)){
                fail = true;
                resposeMap.put("expiredCode", true);
                resposeMap.put("Fail", true);
                return new ResponseEntity<>(resposeMap, HttpStatus.OK);
            }
            else{
                //Check code (true, false)
                if(!emailService.checkVerificationCodeMatch(codeFromClient, this.verificationEmailStructure)){
                    fail = true;
                    resposeMap.put("wrongCode", true);
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
                resposeMap.put("overlapped", true);
                return new ResponseEntity<>(resposeMap, HttpStatus.OK);
            }
            else {
                //Set new pass into database
                users new_users = usersService.updatePasswordByUsername(this.username, encoder.encode(new_pass));
                resposeMap.put("successful", true);
                resposeMap.put("Fail", false);
                return new ResponseEntity<>(resposeMap, HttpStatus.OK);
            }
        }
        return null;
    }
}
