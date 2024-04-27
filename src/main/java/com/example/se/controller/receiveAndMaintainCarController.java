package com.example.se.controller;

import com.example.se.model.brands;
import com.example.se.model.cars;
import com.example.se.model.dataDTO.Form1InformationDTO;
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
     * Get all record
     * @return
     * A map containing all maintenance records
     */
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

    /**
     * Process when submit add form 1
     * @param request: HttpServletRequest object
     * @return
     * Redirect to some HTML pages
     */
    @PostMapping("/add-form1")
    public String handleFormAdd(HttpServletRequest request) {

        try {
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

            owners newOwner = new owners();
            newOwner.setOwnerName(name);
            newOwner.setOwnerAddress(address);
            newOwner.setOwnerPhoneNumber(phone);
            newOwner.setOwnerEmail(email);
            newOwner.setUsername(authentication.getName());

            newOwner = this.ownersService.save(newOwner);

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

        } catch (Exception e) {
            return "redirect:/home?full=true";
        }
    }

    private Form1InformationDTO oldDataChange = new Form1InformationDTO();

    /**
     * Get old data to compare
     * @param oldData: DTO from client
     * @return
     * Status ok
     */
    @PostMapping("/get-old-data-change-form1")
    public ResponseEntity<Map<String, Object>> getOldData(@RequestBody Form1InformationDTO oldData) {
        this.oldDataChange = oldData;
        HashMap<String, Object> response = new HashMap<>();
        response.put("ok", "ok");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Process when submit change form 1
     * @param request: HttpServletRequest object
     * @return
     * Redirect to some HTML pages
     */
    @PostMapping("/change-form1")
    public String handleFormChange(HttpServletRequest request) {

        Form1InformationDTO oldData = this.oldDataChange;

        HashMap<String, Object> response = new HashMap<>();

        String name = request.getParameter("change_name");
        String phone = request.getParameter("change_phone");
        String email = request.getParameter("change_email");
        String address = request.getParameter("change_address");
        String vehicleLicenseNumber = request.getParameter("change_vehicleLicenseNumber");
        String vehicleBrand = request.getParameter("change_vehicleBrand");

        cars oldCars = this.carsService.findByLicensePlate(oldData.getLicense()).get(0);
        oldCars.setLicensePlate(vehicleLicenseNumber);

        brands oldBrands = this.brandsService.findByBrandName(oldData.getBrand());
        int brandID = 0;
        if(oldBrands.getCarsList().isEmpty()) {
            oldBrands.setBrandID(oldBrands.getBrandID());
            oldBrands.setBrandName(vehicleBrand);
            brandID = this.brandsService.save(oldBrands).getBrandID();
        }
        else {
            brands newBrands = this.brandsService.findByBrandName(vehicleBrand);
            if(newBrands == null){
                newBrands = new brands();
                newBrands.setBrandName(vehicleBrand);
                brandID = this.brandsService.save(newBrands).getBrandID();
            }
            else {
                brandID = newBrands.getBrandID();
            }
        }

        oldCars.setBrandID(brandID);

        owners oldOwner = this.ownersService.findByOwnerEmail(oldData.getEmail());
        oldOwner.setOwnerID(oldOwner.getOwnerID());
        oldOwner.setOwnerName(name);
        oldOwner.setOwnerAddress(address);
        oldOwner.setOwnerPhoneNumber(phone);
        oldOwner.setOwnerEmail(email);

        this.ownersService.save(oldOwner);
        this.carsService.save(oldCars);

        return "redirect:/home";
    }

    /**
     * Delete a row
     * @param Form1InformationDTO: Form 1 DTO
     * @return
     * Status and message
     */
    @ResponseBody
    @PostMapping("/delete-row-form1")
    public ResponseEntity<Map<String, String>> deleteRowForm1(@RequestBody Form1InformationDTO Form1InformationDTO) {
        HashMap<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "success");

        maintenanceRecords maintenanceRecords = this.maintenanceService.findByRecordID(Form1InformationDTO.getRecordID());
        int carID = maintenanceRecords.getCarID();
        int ownerID = this.carsService.findByCarID(carID).getOwnerID();

        this.maintenanceService.deleteByRecordID(Form1InformationDTO.getRecordID());
        this.carsService.deleteByCarID(carID);
        this.ownersService.deleteByOwnerID(ownerID);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
