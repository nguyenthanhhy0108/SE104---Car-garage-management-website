package com.example.se.controller;
import com.example.se.config.securityConfig;
import com.example.se.model.authorities;
import com.example.se.model.userDetails;
import com.example.se.model.users;
import com.example.se.service.authoritiesService;
import com.example.se.service.userDetailsService;
import com.example.se.service.usersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class registerController {
    //Identify internal attribute
    private final userDetailsService userDetailsService;
    private final usersService UsersService;
    private final authoritiesService AuthoritiesService;
    private final PasswordEncoder encoder = securityConfig.passwordEncoder();

    //Initialize internal attribute

    /**
     * Dependency Injection
     * @param userDetailsService: user_detailsService object
     * @param usersService: usersService object
     * @param authoritiesService: authoritiesService object
     */
    @Autowired
    public registerController(userDetailsService userDetailsService,
                              usersService usersService,
                              authoritiesService authoritiesService) {
        this.userDetailsService = userDetailsService;
        this.UsersService = usersService;
        this.AuthoritiesService = authoritiesService;
    }

    /**
     * Redirect to register page
     * @return
     * register.html
     */
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    /**
     * Execute after submit register form
     * @param model: Model object for interacting with UI
     * @param request: HttpServletRequest object
     * @return
     * Some html page
     */
    @PostMapping("/register")
    public String register(Model model,
                           HttpServletRequest request) {
        //Get some parameter: username, password, email
        //Check space in parameters
        //Check username and email in database by Service layer method
        //  if they are overlapped: Response error message and redirect to register page
        //  else: Response successful message and eliminate some existed attribute in session and redirect to login page
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        model.addAttribute("username", username);
        model.addAttribute("email", email);

        if (this.UsersService.checkUsernameExist(username) && this.userDetailsService.checkEmailExists(email)) {
            model.addAttribute("email_used", "Email is already used");
            model.addAttribute("username_used", "Phone number is already used");
            return "register";
        }
        if (this.UsersService.checkUsernameExist(username)) {
            model.addAttribute("username_used", "Phone number is already used");
            return "register";
        }
        if (this.userDetailsService.checkEmailExists(email)) {
            model.addAttribute("email_used", "Email is already used");
            return "register";
        }

        users new_user = UsersService.save(new users(username, encoder.encode(password), 1));

        authorities authorities = AuthoritiesService.save(new authorities(username, "ROLE_USER"));

        userDetails new_user_details = userDetailsService.save(new userDetails(username, email, "", ""));

        HttpSession session = request.getSession();
        session.setAttribute("create_account_successfully", true);
        session.removeAttribute("password_wrong");
        session.removeAttribute("username_not_exist");
        return "login";
    }
}


