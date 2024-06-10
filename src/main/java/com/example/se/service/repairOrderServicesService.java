package com.example.se.service;

import com.example.se.model.repairOrderServices;

import java.util.List;

public interface repairOrderServicesService {
    /**
     * Find by order number
     * @param orderNumber: int
     * @return
     * repairOrderServices object
     */
    repairOrderServices findByOrderNumber(int orderNumber);

    repairOrderServices save(repairOrderServices repairOrderServices);

    repairOrderServices copy(repairOrderServices repairOrderServices);

    void delete(repairOrderServices repairOrderServices);

    List<repairOrderServices> findByServiceID(int serviceID);
}
