package com.example.se.controller;

import com.example.se.model.brands;
import com.example.se.model.cars;
import com.example.se.model.maintenanceRecords;
import com.example.se.model.owners;
import com.example.se.service.maintenanceRecordsService;
import com.example.se.service.brandsService;
import com.example.se.service.carsService;
import com.example.se.service.ownersService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.*;

@Controller
public class receiveAndMaintainCarController {

    private final maintenanceRecordsService maintenanceService;
    private final ownersService ownersService;
    private final carsService carsService;
    private final brandsService brandsService;

    @Autowired
    public receiveAndMaintainCarController(maintenanceRecordsService maintenanceService,
                                           ownersService ownersService,
                                           carsService carsService,
                                           brandsService brandsService) {
        this.maintenanceService = maintenanceService;
        this.ownersService = ownersService;
        this.carsService = carsService;
        this.brandsService = brandsService;
    }

    @ResponseBody
    @GetMapping("/get-all-records")
    public ResponseEntity<List<Map<String, Object>>> getAllMaintenanceRecords() {

        List<Map<String, Object>> response = new ArrayList<>();

        List<maintenanceRecords> maintenanceRecords = this.maintenanceService.findAllRecords();
        for (maintenanceRecords maintenanceRecord : maintenanceRecords) {
            Map<String, Object> map = new HashMap<>();

            map.put("ID", maintenanceRecord.getRecordID());
            map.put("Name", this.ownersService
                    .findByOwnerID(
                            this.carsService
                                    .findByCarID(maintenanceRecord.getCarID())
                                    .getOwnerID())
                    .getOwnerName());
            map.put("Phone", this.ownersService
                    .findByOwnerID(
                            this.carsService
                                    .findByCarID(maintenanceRecord.getCarID())
                                    .getOwnerID())
                    .getOwnerPhoneNumber());
            map.put("Email", this.ownersService
                    .findByOwnerID(
                            this.carsService
                                    .findByCarID(maintenanceRecord.getCarID())
                                    .getOwnerID())
                    .getOwnerEmail());
            map.put("Address", this.ownersService
                    .findByOwnerID(
                            this.carsService
                                    .findByCarID(maintenanceRecord.getCarID())
                                    .getOwnerID())
                    .getOwnerAddress());
            map.put("Vehicle license number", this.carsService
                    .findByCarID(
                            maintenanceRecord.getCarID())
                    .getLicensePlate());
            map.put("Vehicle brand", this.brandsService
                    .findByBrandID(this.carsService
                            .findByCarID(maintenanceRecord.getCarID())
                            .getBrandID())
                    .getBrandName());
            map.put("Date", maintenanceRecord.getDateReceived());

            response.add(map);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add-form")
    public String handleFormAdd(HttpServletRequest request) {

        String name = request.getParameter("name");
        String date = request.getParameter("date");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String vehicleLicenseNumber = request.getParameter("vehicleLicenseNumber");
        String vehicleBrand = request.getParameter("vehicleBrand");

        List<cars> carsList = this.carsService.findByLicensePlate(vehicleLicenseNumber);

        if(!carsList.isEmpty()) {
            return "redirect:/home?exist=True";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        owners newOwner = this.ownersService.findByUsername(authentication.getName());

        if(newOwner == null) {
            newOwner = new owners();
            newOwner.setOwnerName(name);
            newOwner.setOwnerAddress(address);
            newOwner.setOwnerPhoneNumber(phone);
            newOwner.setOwnerEmail(email);
            newOwner.setUsername(authentication.getName());

            newOwner = this.ownersService.save(newOwner);
        }

        int ownerId = newOwner.getOwnerID();

        brands newBrand = this.brandsService.findByBrandName(vehicleBrand);
        if(newBrand == null) {
            newBrand = new brands();
            newBrand.setBrandName(vehicleBrand);
            newBrand = this.brandsService.save(newBrand);
        }

        int brandID = newBrand.getBrandID();

        cars newCar = new cars();
        newCar.setLicensePlate(vehicleLicenseNumber);
        newCar.setOwnerID(ownerId);
        newCar.setBrandID(brandID);

        newCar = this.carsService.save(newCar);

        int carID = newCar.getCarID();

        maintenanceRecords maintenanceRecord = new maintenanceRecords();

        maintenanceRecord.setCarID(carID);
        LocalDate dateParsed = LocalDate.parse(date);
        maintenanceRecord.setDateReceived(dateParsed);

        maintenanceRecord = this.maintenanceService.save(maintenanceRecord);

        return "redirect:/home";
    }
}
