package com.example.se.controller;

import com.example.se.model.cars;
import com.example.se.model.dataDTO.CarAndDebtDTO;
import com.example.se.model.dataDTO.CarDTO;
import com.example.se.model.dataDTO.Form3InformationDTO;
import com.example.se.model.owners;
import com.example.se.service.carsService;
import com.example.se.service.ownersService;
import com.example.se.service.receiptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class vehicleController {

    private final ownersService ownerService;
    private final carsService carsServices;
    private final receiptsService receiptsService;

    /**
     * Dependency Injection
     * @param ownerService: ownerService object
     * @param carsServices: carsServices object
     */
    @Autowired
    public vehicleController(ownersService ownerService,
                             carsService carsServices,
                             receiptsService receiptsService) {
        this.ownerService = ownerService;
        this.carsServices = carsServices;
        this.receiptsService = receiptsService;
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
}
