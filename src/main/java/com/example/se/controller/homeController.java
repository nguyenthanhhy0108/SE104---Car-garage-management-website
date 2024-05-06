package com.example.se.controller;

import com.example.se.model.dataDTO.CarDTO;
import com.example.se.model.dataDTO.PartDTO;
import com.example.se.model.maintenanceRecords;
import com.example.se.model.owners;
import com.example.se.model.parts;
import com.example.se.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.Authenticator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class homeController {

    private final userDetailsService userDetailsService;
    private final usersService userService;
    private final maintenanceRecordsService maintenanceService;
    private final ownersService ownersService;
    private final carsService carsService;
    private final brandsService brandsService;
    private final partsService partsService;

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
                          partsService partsService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.maintenanceService = maintenanceService;
        this.ownersService = ownersService;
        this.carsService = carsService;
        this.brandsService = brandsService;
        this.partsService = partsService;
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
     * Get all record
     * @return
     * A map containing all maintenance records
     */
    @ResponseBody
    @GetMapping("/get-all-records")
    public ResponseEntity<List<Map<String, Object>>> getAllMaintenanceRecords() {

        List<Map<String, Object>> response = new ArrayList<>();

        List<owners> owners = new ArrayList<>();

        List<maintenanceRecords> maintenanceRecords = this.maintenanceService.findAllRecords();
        for (maintenanceRecords maintenanceRecord : maintenanceRecords) {
            owners foundOwner = this.ownersService
                    .findByOwnerID(
                            this.carsService
                                    .findByCarID(maintenanceRecord.getCarID())
                                    .getOwnerID());
            if(!owners.contains(foundOwner)) {
                owners.add(foundOwner);
            }
        }

        for(owners owner : owners) {
            Map<String, Object> record = new HashMap<>();
            record.put("ID", owner.getOwnerID());
            record.put("Name", owner.getOwnerName());
            record.put("Phone", owner.getOwnerPhoneNumber());
            record.put("Email", owner.getOwnerEmail());
            record.put("Address", owner.getOwnerAddress());
            List<CarDTO> carDTOS = this.carsService.toDTO(
                    this.carsService.findByOwnerID(owner.getOwnerID())
            );
            record.put("Cars", carDTOS);
            response.add(record);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/get-all-parts")
    public ResponseEntity<List<PartDTO>> getAllParts() {
        List<PartDTO> response = new ArrayList<>();
        List<parts> parts = this.partsService.findAll();
        for (parts part : parts) {
            response.add(part.toDTO());
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
