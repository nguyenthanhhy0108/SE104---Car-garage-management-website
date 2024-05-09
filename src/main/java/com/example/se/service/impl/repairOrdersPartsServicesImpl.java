package com.example.se.service.impl;

import com.example.se.model.repairOrdersParts;
import com.example.se.repository.repairOrdersPartsRepository;
import com.example.se.service.repairOrdersPartsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class repairOrdersPartsServicesImpl implements repairOrdersPartsServices {

    private final repairOrdersPartsRepository repairOrdersPartsRepository;

    @Autowired
    public repairOrdersPartsServicesImpl(repairOrdersPartsRepository repairOrdersPartsRepository) {
        this.repairOrdersPartsRepository = repairOrdersPartsRepository;
    }

    /**
     * Implement find by order number
     * @param orderNumber: int
     * @return
     * repairOrdersParts object
     */
    @Override
    public repairOrdersParts findByOrderNumber(int orderNumber) {
        return this.repairOrdersPartsRepository.findByOrderNumber(orderNumber);
    }
}
