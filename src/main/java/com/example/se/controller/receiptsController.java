package com.example.se.controller;
import com.example.se.model.cars;
import com.example.se.model.owners;
import com.example.se.model.receipts;
import com.example.se.service.receiptsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.example.se.service.carsService;
import com.example.se.service.ownersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class receiptsController {
    private final receiptsService receiptsService;
    private  final ownersService ownersService;
    private  final carsService carsService;

    @Autowired
    public receiptsController(receiptsService receiptsService,
                              ownersService ownersService,
                              carsService carsService) {
        this.receiptsService = receiptsService;
        this.ownersService = ownersService;
        this.carsService = carsService;
    }

    /**
     * Get all payments method
     * @return
     * Json object to client
     */
    @ResponseBody
    @GetMapping("/get-all-payment")
    public ResponseEntity<List<Map<String, Object>>> getAllReceipts() {
        List<Map<String, Object>> response = new ArrayList<>();

        List<receipts> allReceipts = receiptsService.findAllReceipts();

        for (receipts receipt : allReceipts) {
            Map<String, Object> map = new HashMap<>();

            int carId = receipt.getCarId();
            cars car = carsService.findByCarID(carId);
            if (car != null) {
                int ownerId = this.carsService
                        .findByCarID(
                                receipt.getCarId())
                        .getOwnerID();
                owners owner = ownersService.findByOwnerID(ownerId);
                if (owner != null) {
                    map.put("Owner Name", owner.getOwnerName());
                    map.put("License Plate", car.getLicensePlate());
                    map.put("Phone Number", owner.getOwnerPhoneNumber());
                    map.put("Email", owner.getOwnerEmail());
                    map.put("Payment Date", receipt.getPaymentdate());
                    map.put("Amount Paid", receipt.getAmountpaid());
                    response.add(map);
                }
            }
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
