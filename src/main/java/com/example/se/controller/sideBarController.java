package com.example.se.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.example.se.service.*;

@Controller
public class sideBarController {

    private final userDetailsService userDetailsService;
    private final usersService userService;
    private final maintenanceRecordsService maintenanceService;
    private final ownersService ownersService;
    private final carsService carsService;
    private final brandsService brandsService;
    private final partsService partsService;
    private final servicesServices servicesServices;
    private final receiptsService receiptsService;

    /**
     * Dependency Injection
     * @param userDetailsService: userDetailsService object
     * @param userService: usersService object
     */
    @Autowired
    public homeController(userDetailsService userDetailsService,
                          usersService userService,
                          maintenanceRecordsService maintenanceService,
                          ownersService ownersService,
                          carsService carsService,
                          brandsService brandsService,
                          partsService partsService,
                          servicesServices servicesService,
                          receiptsService receiptsService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.maintenanceService = maintenanceService;
        this.ownersService = ownersService;
        this.carsService = carsService;
        this.brandsService = brandsService;
        this.partsService = partsService;
        this.servicesServices = servicesService;
        this.receiptsService = receiptsService;
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

    /**
     * Get vehicle page
     * @return
     * A HTML
     */
    @GetMapping("/vehicle")
    String getVehiclePage() {
        return "vehicle";
    }
    @GetMapping("/report")
    String getFormReport() {
        return "report";
    }
    @GetMapping("/policy")
    String getFormPolicy() {
        return "policy";
    }
}
