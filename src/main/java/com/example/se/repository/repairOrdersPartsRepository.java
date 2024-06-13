package com.example.se.repository;

import com.example.se.model.repairOrdersParts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface repairOrdersPartsRepository extends JpaRepository<repairOrdersParts, Integer> {
    /**
     * Find by order number
     * @param orderNumber: int
     * @return
     * repairOrdersParts object
     */
    repairOrdersParts findByOrderNumber(int orderNumber);

    List<repairOrdersParts> findByPartID(int partID);
}
