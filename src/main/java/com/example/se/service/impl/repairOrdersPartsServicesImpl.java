package com.example.se.service.impl;

import com.example.se.model.repairOrdersParts;
import com.example.se.repository.repairOrdersPartsRepository;
import com.example.se.service.repairOrdersPartsServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Transactional
    @Override
    public repairOrdersParts save(repairOrdersParts repairOrdersParts) {
        return this.repairOrdersPartsRepository.save(repairOrdersParts);
    }

    @Override
    public repairOrdersParts copy(repairOrdersParts repairOrdersParts) {
        repairOrdersParts newRepairOrdersParts = new repairOrdersParts();
        newRepairOrdersParts.setOrderNumber(repairOrdersParts.getOrderNumber());
        newRepairOrdersParts.setQuantity(repairOrdersParts.getQuantity());
        newRepairOrdersParts.setPartID(repairOrdersParts.getPartID());

        return newRepairOrdersParts;
    }

    @Override
    public void delete(repairOrdersParts repairOrdersParts) {
        this.repairOrdersPartsRepository.delete(repairOrdersParts);
    }

    @Override
    public List<repairOrdersParts> findByPartID(int partID) {
        return this.repairOrdersPartsRepository.findByPartID(partID);
    }
}
