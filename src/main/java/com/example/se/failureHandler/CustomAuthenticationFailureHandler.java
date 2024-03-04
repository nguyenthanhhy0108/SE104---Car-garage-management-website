package com.example.se.failureHandler;
import com.example.se.config.SecurityConfig;
import com.example.se.model.users;
import com.example.se.service.usersService;
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

import java.io.IOException;
import java.util.List;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private usersService UsersService;
    private final PasswordEncoder encoder = SecurityConfig.passwordEncoder();
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        super.onAuthenticationFailure(request, response, exception);

        String username = request.getParameter("username");
        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        session.setAttribute("username_not_exist", false);
        session.setAttribute("password_wrong", false);

        List<users> accounts = UsersService.findByUsername(username);
        if(accounts.isEmpty()){
            session.setAttribute("username_not_exist", true);
            session.removeAttribute("password_wrong");
        }
        else{
            String UserPassword = accounts.get(0).getPassword();
            if(!encoder.matches(request.getParameter("password"), UserPassword)){
                session.setAttribute("password_wrong", true);
                session.removeAttribute("username_not_exist");
            }
        }
    }
}
