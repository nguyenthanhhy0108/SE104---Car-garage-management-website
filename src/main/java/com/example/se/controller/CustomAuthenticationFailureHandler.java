package com.example.se.controller;
import org.springframework.ui.Model;
import com.example.se.model.account;
import com.example.se.repository.accountRepository;
import com.example.se.service.accountDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.io.IOException;
import java.util.List;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private accountDetails AccountDetails;
    @Autowired
    private accountRepository AccountRepository;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        super.onAuthenticationFailure(request, response, exception);

        String username = request.getParameter("username");
        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        session.setAttribute("username_not_exist", false);
        session.setAttribute("password_wrong", false);

        List<account> accounts = AccountRepository.findByUsername(username);
        if(accounts.isEmpty()){
            session.setAttribute("username_not_exist", true);
            session.removeAttribute("password_wrong");
        }
        else{
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String CurrentPassword = passwordEncoder.encode(request.getParameter("password"));
            String UserPassword = accounts.get(0).getPassword();
            if(!CurrentPassword.equals(UserPassword)){
                session.setAttribute("password_wrong", true);
                session.removeAttribute("username_not_exist");
            }
        }
    }
}
