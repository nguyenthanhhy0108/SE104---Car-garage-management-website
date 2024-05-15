package com.example.se.service.impl;

import com.example.se.model.repairOrderServices;
import com.example.se.repository.repairOrderServicesRepository;
import com.example.se.service.repairOrderServicesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class repairOrderServicesServiceImpl implements repairOrderServicesService {

    private final repairOrderServicesRepository serviceRepository;

    @Autowired
    public repairOrderServicesServiceImpl(repairOrderServicesRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public repairOrderServices findByOrderNumber(int orderNumber) {
        return this.serviceRepository.findByOrderNumber(orderNumber);
    }

    @Transactional
    @Override
    public repairOrderServices save(repairOrderServices repairOrderServices) {
        return this.serviceRepository.save(repairOrderServices);
    }
}
