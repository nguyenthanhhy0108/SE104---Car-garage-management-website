package com.example.se.controller;

import com.example.se.model.brands;
import com.example.se.model.cars;
import com.example.se.model.dataDTO.CarDTO;
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
            LocalDate date = LocalDate.now();
            maintenanceRecord.setDateReceived(date);

            maintenanceRecord = this.maintenanceService.save(maintenanceRecord);

            return "redirect:/home";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home?full=true";
        }
    }

//    private Form1InformationDTO oldDataChange = new Form1InformationDTO();
//
//    /**
//     * Get old data to compare
//     * @param oldData: DTO from client
//     * @return
//     * Status ok
//     */
//    @PostMapping("/get-old-data-change-form1")
//    public ResponseEntity<Map<String, Object>> getOldData(@RequestBody Form1InformationDTO oldData) {
//        this.oldDataChange = oldData;
//        HashMap<String, Object> response = new HashMap<>();
//        response.put("ok", "ok");
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    /**
//     * Process when submit change form 1
//     * @param request: HttpServletRequest object
//     * @return
//     * Redirect to some HTML pages
//     */
//    @PostMapping("/change-form1")
//    public String handleFormChange(HttpServletRequest request) {
//
//        Form1InformationDTO oldData = this.oldDataChange;
//
//        HashMap<String, Object> response = new HashMap<>();
//
//        String name = request.getParameter("change_name");
//        String phone = request.getParameter("change_phone");
//        String email = request.getParameter("change_email");
//        String address = request.getParameter("change_address");
//        String vehicleLicenseNumber = request.getParameter("change_vehicleLicenseNumber");
//        String vehicleBrand = request.getParameter("change_vehicleBrand");
//
//        cars oldCars = this.carsService.findByLicensePlate(oldData.getLicense()).get(0);
//        oldCars.setLicensePlate(vehicleLicenseNumber);
//
//        brands oldBrands = this.brandsService.findByBrandName(oldData.getBrand());
//        int brandID = 0;
//        if(oldBrands.getCarsList().isEmpty()) {
//            oldBrands.setBrandID(oldBrands.getBrandID());
//            oldBrands.setBrandName(vehicleBrand);
//            brandID = this.brandsService.save(oldBrands).getBrandID();
//        }
//        else {
//            brands newBrands = this.brandsService.findByBrandName(vehicleBrand);
//            if(newBrands == null){
//                newBrands = new brands();
//                newBrands.setBrandName(vehicleBrand);
//                brandID = this.brandsService.save(newBrands).getBrandID();
//            }
//            else {
//                brandID = newBrands.getBrandID();
//            }
//        }
//
//        oldCars.setBrandID(brandID);
//
//        owners oldOwner = this.ownersService.findByOwnerEmail(oldData.getEmail());
//        oldOwner.setOwnerID(oldOwner.getOwnerID());
//        oldOwner.setOwnerName(name);
//        oldOwner.setOwnerAddress(address);
//        oldOwner.setOwnerPhoneNumber(phone);
//        oldOwner.setOwnerEmail(email);
//
//        this.ownersService.save(oldOwner);
//        this.carsService.save(oldCars);
//
//        return "redirect:/home";
//    }

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

            this.maintenanceService.deleteByRecordID(maintenanceRecord.getRecordID());
            this.carsService.deleteByCarID(toRemove.getCarID());

            success = true;
        } catch (Exception e) {
            success = false;
            e.printStackTrace();
        }
        response.put("success", success);
        return ResponseEntity.ok(response);
    }
}
