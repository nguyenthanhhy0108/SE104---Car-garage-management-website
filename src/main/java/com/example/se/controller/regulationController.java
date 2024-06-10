package com.example.se.controller;

import com.example.se.model.*;
import com.example.se.model.dataDTO.BrandDTO;
import com.example.se.model.dataDTO.PartDTO;
import com.example.se.model.dataDTO.ServiceDTO;
import com.example.se.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class regulationController {
    private final partsService partsService;
    private final servicesServices servicesService;
    private final repairOrderServicesService repairOrderServicesService;
    private final repairOrdersPartsServices repairOrdersPartsService;
    private final brandsService brandsService;
    private final triggerValueServices triggerValueServices;

    @Autowired
    public regulationController(partsService partsService,
                                servicesServices servicesService,
                                repairOrderServicesService repairOrderServicesService,
                                repairOrdersPartsServices repairOrdersPartsService,
                                brandsService brandsService,
                                triggerValueServices triggerValueServices) {
        this.partsService = partsService;
        this.servicesService = servicesService;
        this.repairOrderServicesService = repairOrderServicesService;
        this.repairOrdersPartsService = repairOrdersPartsService;
        this.brandsService = brandsService;
        this.triggerValueServices = triggerValueServices;
    }

    @PostMapping("/add-part")
    @ResponseBody
    ResponseEntity<Boolean> addPart(@RequestBody(required = true) PartDTO partDTO, @RequestParam("number_initialized") int num) {
        parts parts = partsService.findByName(partDTO.getPartName());

        boolean result = false;
        if(parts == null) {
            parts newPart = new parts();
            newPart.setName(partDTO.getPartName());
            newPart.setPrice(partDTO.getPrice());
            newPart.setBefore(num);
            newPart.setUsed(0);
            partsService.save(newPart);

            result = true;
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add-service")
    ResponseEntity<Boolean> addService(@RequestBody ServiceDTO serviceDTO) {
        services services = servicesService.findByServiceName(serviceDTO.getServiceName());
        boolean result = false;
        if(services == null) {
            services newService = new services();
            newService.setServiceName(serviceDTO.getServiceName());
            newService.setServiceCost(serviceDTO.getServiceCost());
            servicesService.save(newService);

            result = true;
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete-part")
    ResponseEntity<Boolean> deletePart(@RequestParam("part_name") String partName) {
        parts parts = partsService.findByName(partName);
        int id = parts.getPartID();
        boolean result = false;
        List<repairOrdersParts> repairOrdersParts = repairOrdersPartsService.findByPartID(id);
        if(repairOrdersParts.isEmpty()) {
            partsService.delete(parts);
            result = true;
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete-service")
    ResponseEntity<Boolean> deleteService(@RequestParam("service_name") String serviceName) {
        services service = servicesService.findByServiceName(serviceName);
        int id = service.getServicesID();
        boolean result = false;
        List<repairOrderServices> repairOrderServices = repairOrderServicesService.findByServiceID(id);
        if(repairOrderServices.isEmpty()) {
            servicesService.delete(service);
            result = true;
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete-brand")
    ResponseEntity<Boolean> deleteBrand(@RequestParam("brand_name") String brandName) {
        brands brands = this.brandsService.findByBrandName(brandName);
        boolean result = false;
        try {
            brandsService.delete(brands);
            result = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/change-day-limit")
    ResponseEntity<Boolean> changeDayLimit(@RequestParam("day_limit") int dayLimit) {
        boolean result = false;
        TriggerValue old = this.triggerValueServices.findByName("c");

        try {
            old.setValue(dayLimit + 1);
            this.triggerValueServices.save(old);
            result = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get-all-brands")
    @ResponseBody
    ResponseEntity<List<BrandDTO>> getAllBrands() {
        List<BrandDTO> result = new ArrayList<>();
        List<brands> brandsList = this.brandsService.findDistinctAll();
        for(brands brand : brandsList) {
            result.add(brand.toDTO());
        }
        return ResponseEntity.ok(result);
    }
}
