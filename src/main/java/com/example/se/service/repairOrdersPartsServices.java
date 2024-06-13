package com.example.se.service;

import com.example.se.model.repairOrdersParts;

import java.util.List;

public interface repairOrdersPartsServices {
    /**
     * Find by order number
     * @param orderNumber: int
     * @return
     * repairOrdersParts object
     */
    repairOrdersParts findByOrderNumber(int orderNumber);

    repairOrdersParts save(repairOrdersParts repairOrdersParts);

    repairOrdersParts copy(repairOrdersParts repairOrdersParts);

    void delete(repairOrdersParts repairOrdersParts);

    List<repairOrdersParts> findByPartID(int partID);

    void deleteByOrderNumber(int orderNumber);
}
