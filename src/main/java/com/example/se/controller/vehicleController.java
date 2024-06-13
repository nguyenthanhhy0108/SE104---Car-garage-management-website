package com.example.se.controller;

import com.example.se.model.*;
import com.example.se.model.dataDTO.*;
import com.example.se.service.*;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class vehicleController {

    private final ownersService ownerService;
    private final carsService carsServices;
    private final receiptsService receiptsService;
    private final brandsService brandsService;
    private final partsService partsService;

    /**
     * Dependency Injection
     * @param ownerService: ownerService object
     * @param carsServices: carsServices object
     */
    @Autowired
    public vehicleController(ownersService ownerService,
                             carsService carsServices,
                             receiptsService receiptsService,
                             brandsService brandsService,
                             partsService partsService) {
        this.ownerService = ownerService;
        this.carsServices = carsServices;
        this.receiptsService = receiptsService;
        this.brandsService = brandsService;
        this.partsService = partsService;
    }


    /**
     * Get first data to FE
     * @return
     * Data
     */
    @GetMapping("/get-all-vehicles")
    @ResponseBody
    ResponseEntity<List<Form3InformationDTO>> getFirstData() {
        List<Form3InformationDTO> data = new ArrayList<>();
        List<owners> ownersList = ownerService.findAll();
        for (owners owner : ownersList) {
            Form3InformationDTO form3InformationDTO = new Form3InformationDTO();
            form3InformationDTO.setID(owner.getOwnerID());
            form3InformationDTO.setName(owner.getOwnerName());

            List<CarAndDebtDTO> carAndDebtDTOList = new ArrayList<>();
            List<cars> cars = carsServices.findByOwnerID(owner.getOwnerID());
            for (cars car : cars) {
                CarAndDebtDTO carAndDebtDTO = new CarAndDebtDTO();
                CarDTO carDTO = this.carsServices.toDTO(car);
                carAndDebtDTO.setLicenseNumber(carDTO.getLicenseNumber());
                carAndDebtDTO.setBrand(carDTO.getBrand());
                carAndDebtDTO.setDebt(this.receiptsService.getTotalDebtOfCarId(car.getCarID()));
                carAndDebtDTOList.add(carAndDebtDTO);
            }
            form3InformationDTO.setCars(carAndDebtDTOList);
            data.add(form3InformationDTO);
        }
        return ResponseEntity.ok(data);
    }

    @GetMapping("/get-data-form5")
    ResponseEntity<Form5_1DTO> getDataForm5_1(@RequestParam("month") int month, @RequestParam("year") int year) {
        List<brands> brandsList = this.brandsService.findDistinctAll();
        List<receipts> receiptsList = this.receiptsService.getByMonthAndYear(month, year);

        double total = 0;
        for (receipts receipt : receiptsList) {
            total += receipt.getAmountpaid();
        }

        Form5_1DTO form51DTO = new Form5_1DTO();
        form51DTO.setTotal(total);

        List<BrandForm5_1DTO> brandForm5_1DTOList = new ArrayList<>();
        for (brands brand : brandsList) {
            BrandForm5_1DTO brandForm5_1DTO = new BrandForm5_1DTO();
            brandForm5_1DTO.setBrand(brand.getBrandName());
            brandForm5_1DTO.setCountFix(Integer.parseInt(this.receiptsService.countDistinctByMonthAndYearAndBrandID(month, year, brand.getBrandID())));
            List<receipts> receiptsList1 = this.receiptsService.getByMonthAndYearAndBrandID(month, year, brand.getBrandID());
            double brandTotal = 0;
            for (receipts receipt : receiptsList1) {
                brandTotal += receipt.getAmountpaid();
            }
            brandForm5_1DTO.setValue(brandTotal);
            brandForm5_1DTO.setRate(brandTotal / total);

            brandForm5_1DTOList.add(brandForm5_1DTO);

            form51DTO.setList(brandForm5_1DTOList);
        }

        return new ResponseEntity<>(form51DTO, HttpStatus.OK);
    }

    @GetMapping("/get-parts-form5")
    ResponseEntity<Form5_2DTO> getPartsForm5() {
        List<parts> partsList = partsService.findAll();
        Form5_2DTO form5_2DTO = new Form5_2DTO();
        List<PartsForm5DTO> partsForm5DTOList = new ArrayList<>();
        for (parts part : partsList) {
            PartsForm5DTO partsForm5DTO = new PartsForm5DTO();
            partsForm5DTO.setPartName(part.getName());
            partsForm5DTO.setBefore(part.getBefore());
            partsForm5DTO.setAfter(part.getBefore() - part.getUsed());
            partsForm5DTO.setUsed(part.getUsed());

            partsForm5DTOList.add(partsForm5DTO);
        }
        form5_2DTO.setPartsForm5List(partsForm5DTOList);
        return new ResponseEntity<>(form5_2DTO, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/add-number-part")
    public boolean addNumberPart(
            @RequestParam("part_name") String partName,
            @RequestParam("number") String numberPart) {

        boolean response = false;

        try {
            parts parts = partsService.findByName(partName);
            if (parts == null) {
                return response;
            }

            int numberToAdd;
            try {
                numberToAdd = Integer.parseInt(numberPart);
            } catch (NumberFormatException e) {
                return response;
            }

            parts.setBefore(parts.getBefore() + numberToAdd);
            partsService.save(parts);

            response = true;
            return response;
        } catch (Exception e) {
            return response;
        }
    }
}
