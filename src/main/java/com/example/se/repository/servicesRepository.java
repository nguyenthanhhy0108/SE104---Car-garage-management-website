package com.example.se.repository;

import com.example.se.model.services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface servicesRepository extends JpaRepository<services, Integer> {
    /**
     * find by service ID
     * @param id: int
     * @return
     * services object
     */
    services findByServicesID(int id);
}
