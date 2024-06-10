package com.example.se.controller;

import com.example.se.model.brands;
import com.example.se.model.cars;
import com.example.se.model.dataDTO.OwnerDTOForm2;
import com.example.se.model.maintenanceRecords;
import com.example.se.model.owners;
import com.example.se.service.maintenanceRecordsService;
import com.example.se.service.brandsService;
import com.example.se.service.carsService;
import com.example.se.service.ownersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Controller
public class receiveAndMaintainCarController {

    private final maintenanceRecordsService maintenanceService;
    private final ownersService ownersService;
    private final carsService carsService;
    private final brandsService brandsService;

    /**
     * Dependency Injection
     * @param maintenanceService: maintenanceRecordsService object
     * @param ownersService: ownersService object
     * @param carsService: carsService object
     * @param brandsService: brandsService object
     */
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

    /**
     * Process when submit add form 1
     * @param request: HttpServletRequest object
     * @return
     * Redirect to some HTML pages
     */
    @PostMapping("/add-form1")
    public String handleFormAdd(HttpServletRequest request) {

        int ownerId = -1;
        int brandID = -1;
        int carID = -1;
        try {
            String name = request.getParameter("name");
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


            owners newOwner = this.ownersService.findByOwnerPhoneNumber(phone);
            if(newOwner == null) {
                newOwner = new owners();
                newOwner.setOwnerName(name);
                newOwner.setOwnerAddress(address);
                newOwner.setOwnerPhoneNumber(phone);
                newOwner.setOwnerEmail(email);
                newOwner.setUsername(authentication.getName());

                newOwner = this.ownersService.save(newOwner);
            }
            ownerId = newOwner.getOwnerID();

            brands newBrand = this.brandsService.findByBrandName(vehicleBrand);
            if(newBrand == null) {
                newBrand = new brands();
                newBrand.setBrandName(vehicleBrand);
                newBrand = this.brandsService.save(newBrand);
            }

            brandID = newBrand.getBrandID();

            cars newCar = new cars();
            newCar.setLicensePlate(vehicleLicenseNumber);
            newCar.setOwnerID(ownerId);
            newCar.setBrandID(brandID);

            newCar = this.carsService.save(newCar);

            carID = newCar.getCarID();

            maintenanceRecords maintenanceRecord = new maintenanceRecords();

            maintenanceRecord.setCarID(carID);
            LocalDate date = LocalDate.now();
            maintenanceRecord.setDateReceived(date);

            maintenanceRecord = this.maintenanceService.save(maintenanceRecord);

            return "redirect:/home";

        } catch (Exception e) {
            e.printStackTrace();
            try {
                carsService.deleteByCarID(carID);
            } catch (Exception e3) {
                e3.printStackTrace();
            }

            try {
                ownersService.deleteByOwnerID(ownerId);
                System.out.println(ownerId);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            try {
                brandsService.delete(brandsService.findByBrandID(brandID));
            } catch (Exception e2) {
                e2.printStackTrace();
            }

            return "redirect:/home?full=true";
        }
    }

    @PostMapping("/change-form1")
    public ResponseEntity<Map<String, Object>> handleFormChange(@RequestBody OwnerDTOForm2 ownerDTOForm2) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "success");

        owners owners = ownersService.findByOwnerPhoneNumber(ownerDTOForm2.getOldPhoneNumber());

        if (ownerDTOForm2.getName() != null) {
            owners.setOwnerName(ownerDTOForm2.getName());
        }
        if (ownerDTOForm2.getPhoneNumber() != null) {
            owners.setOwnerPhoneNumber(ownerDTOForm2.getPhoneNumber());
        }
        if (ownerDTOForm2.getEmail() != null) {
            owners.setOwnerEmail(ownerDTOForm2.getEmail());
        }
        if (ownerDTOForm2.getAddress() != null) {
            owners.setOwnerAddress(ownerDTOForm2.getAddress());
        }

        this.ownersService.save(owners);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Delete a row
     * @param licensePlate: Request Param String
     * @return
     * Success or error
     */
    @ResponseBody
    @DeleteMapping("/delete-row-form1")
    public ResponseEntity<Map<String, Object>> deleteRowForm1(@RequestParam(value = "license_plate") String licensePlate) {
        Map<String, Object> response = new HashMap<>();
        boolean success = false;
        try {
            List<cars> carsList = this.carsService.findByLicensePlate(licensePlate);
            cars toRemove = carsList.get(0);
            maintenanceRecords maintenanceRecord = this.maintenanceService.findByCarID(toRemove.getCarID());

            try {
                this.maintenanceService.deleteByRecordID(maintenanceRecord.getRecordID());
                this.carsService.deleteByCarID(toRemove.getCarID());
            } catch (Exception e) {
                success = false;
                response.put("success", success);
                e.printStackTrace();
                return ResponseEntity.ok(response);
            }

            success = true;
        } catch (Exception e) {
            success = false;
            e.printStackTrace();
        }
        response.put("success", success);
        return ResponseEntity.ok(response);
    }
}
