package com.example.se.controller;
import com.example.se.model.*;
import com.example.se.model.dataDTO.Form2InformationDTO;
import com.example.se.model.dataDTO.OrderDetailsDTO;
import com.example.se.model.dataDTO.OrderInDayDTO;
import com.example.se.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.*;

@Controller
public class receiptsController {
    private final receiptsService receiptsService;
    private final ownersService ownersService;
    private final carsService carsService;
    private final partsService partsService;
    private final repairOrdersPartsServices repairOrdersPartsService;
    private final repairOrderServicesService repairOrderServicesService;
    private final servicesServices servicesServices;

    @Autowired
    public receiptsController(receiptsService receiptsService,
                              ownersService ownersService,
                              carsService carsService,
                              repairOrdersPartsServices repairOrdersPartsService,
                              partsService partsService,
                              repairOrderServicesService repairOrderServicesService,
                              servicesServices servicesService) {
        this.receiptsService = receiptsService;
        this.ownersService = ownersService;
        this.carsService = carsService;
        this.repairOrdersPartsService = repairOrdersPartsService;
        this.partsService = partsService;
        this.repairOrderServicesService = repairOrderServicesService;
        this.servicesServices = servicesService;
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

    @ResponseBody
    @GetMapping("get-all-receipt")
    public ResponseEntity<List<Form2InformationDTO>> getAllReceipt() {
        List<Form2InformationDTO> response = new ArrayList<>();
        List<Integer> listCarID = receiptsService.findAllCarID();
        for (int carID : listCarID) {
            Form2InformationDTO form2InformationDTO = new Form2InformationDTO();
            form2InformationDTO.setLicenseNumber(this.carsService
                    .findByCarID(carID).getLicensePlate());

            List<OrderInDayDTO> orderInDayDTOList = new ArrayList<>();

            List<LocalDate> allDatesRaw = receiptsService.findAllDatesByCarId(carID);

            Set<LocalDate> allDates = new HashSet<>(allDatesRaw);

            for (LocalDate date : allDates) {
                OrderInDayDTO orderInDayDTO = new OrderInDayDTO();
                orderInDayDTO.setOrderDate(date);

                List<OrderDetailsDTO> orderDetailsList = new ArrayList<>();

                List<Integer> allOrderNumberInDay = this.receiptsService.findAllOrderIDByCarIdAndDate(carID, date);

                for (Integer orderNumber : allOrderNumberInDay) {
                    OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
                    orderDetailsDTO.setOrderNumber(orderNumber);
                    repairOrdersParts repairOrdersParts = this.repairOrdersPartsService.findByOrderNumber(orderNumber);

                    orderDetailsDTO.setQuantity(repairOrdersParts.getQuantity());
                    orderDetailsDTO.setPart(this.partsService
                            .findByPartID(repairOrdersParts.getPartID())
                            .toDTO());

                    repairOrderServices repairOrderServices = this.repairOrderServicesService
                            .findByOrderNumber(orderNumber);

                    orderDetailsDTO.setService(
                            this.servicesServices
                                    .findByServicesID(repairOrderServices.getServiceID())
                                    .toDTO());

                    orderDetailsDTO.setTotal(
                            orderDetailsDTO.getQuantity() * orderDetailsDTO.getPart().getPrice()
                                    + orderDetailsDTO.getService().getServiceCost());

                    orderDetailsList.add(orderDetailsDTO);
                    orderInDayDTO.setOrderDetails(orderDetailsList);

                }
                orderInDayDTOList.add(orderInDayDTO);
                orderInDayDTO.setOrderDetails(orderDetailsList);
            }

            form2InformationDTO.setDates(orderInDayDTOList);
            response.add(form2InformationDTO);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
