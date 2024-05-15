package com.example.se.service;

import com.example.se.model.repairOrderServices;

public interface repairOrderServicesService {
    /**
     * Find by order number
     * @param orderNumber: int
     * @return
     * repairOrderServices object
     */
    repairOrderServices findByOrderNumber(int orderNumber);

    repairOrderServices save(repairOrderServices repairOrderServices);
}
