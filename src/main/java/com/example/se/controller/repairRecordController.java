package com.example.se.controller;

import com.example.se.model.*;
import com.example.se.model.receipts;
import com.example.se.service.*;
import com.example.se.service.receiptsService;
import com.example.se.service.repairOrdersPartsServices;
import com.example.se.service.repairOrderServicesService;
import com.example.se.service.carsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

@Controller
public class repairRecordController {

    private final receiptsService receiptsService;
    private final repairOrdersPartsServices repairPartsService;
    private final repairOrderServicesService repairOrderServicesService;
    private final carsService carsService;
    private final partsService partsService;
    private final servicesServices servicesServices;

    private String licenseNumber;

    @Autowired
    public repairRecordController(receiptsService receiptsService,
                                  repairOrdersPartsServices repairPartsService,
                                  repairOrderServicesService repairOrderServicesService,
                                  carsService carsService,
                                  servicesServices servicesServices,
                                  partsService partsService) {
        this.receiptsService = receiptsService;
        this.repairPartsService = repairPartsService;
        this.repairOrderServicesService = repairOrderServicesService;
        this.carsService = carsService;
        this.servicesServices = servicesServices;
        this.partsService = partsService;
    }

    @ResponseBody
    @GetMapping("/get-license-number")
    public void getLicenseNumber(@RequestParam(name = "license_number") String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @PostMapping("/add-repair-order")
    String addRepairOrder(HttpServletRequest request) {

        String note = request.getParameter("note");
        String equipment = request.getParameter("equipment");
        String quantity = request.getParameter("quantity");
        String service = request.getParameter("service");

        receipts receipts = new receipts();
        receipts.setNote(note);
        receipts.setCarId(this.carsService.findByLicensePlate(this.licenseNumber).get(0).getCarID());
        receipts.setDate(LocalDate.now());
        receipts.setAmountpaid(0);
        receipts.setPaymentdate(LocalDate.now());
        receipts.setAmountOwed(0);

        receipts = receiptsService.save(receipts);

        repairOrdersParts repairParts = new repairOrdersParts();
        repairParts.setOrderNumber(receipts.getOrdernumber());
        repairParts.setQuantity(Integer.parseInt(quantity));
        repairParts.setPartID(partsService.findByName(equipment).getPartID());

        this.repairPartsService.save(repairParts);

        repairOrderServices repairOrderServices = new repairOrderServices();
        repairOrderServices.setOrderNumber(receipts.getOrdernumber());
        repairOrderServices.setServiceID(this.servicesServices.findByServiceName(service).getServicesID());

        this.repairOrderServicesService.save(repairOrderServices);

        return "home";
    }

}
