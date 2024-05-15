package com.example.se.service;

import com.example.se.model.repairOrdersParts;

public interface repairOrdersPartsServices {
    /**
     * Find by order number
     * @param orderNumber: int
     * @return
     * repairOrdersParts object
     */
    repairOrdersParts findByOrderNumber(int orderNumber);

    repairOrdersParts save(repairOrdersParts repairOrdersParts);
}
