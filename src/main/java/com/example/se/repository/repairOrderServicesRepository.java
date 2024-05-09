package com.example.se.repository;

import com.example.se.model.repairOrderServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repairOrderServicesRepository extends JpaRepository<repairOrderServices,Integer> {

    /**
     * Find by order number
     * @param orderNumber: int
     * @return
     * repairOrderServices object
     */
    repairOrderServices findByOrderNumber(int orderNumber);
}
