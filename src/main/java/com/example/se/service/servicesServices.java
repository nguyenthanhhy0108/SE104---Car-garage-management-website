package com.example.se.service;

import com.example.se.model.services;

import java.util.List;

public interface servicesServices {
    /**
     * Find all services available
     * @return
     * List services objects
     */
    List<services> findAll();

    /**
     * Find by service ID
     * @param id: int
     * @return
     * Services object
     */
    services findByServicesID(int id);

    services findByServiceName(String name);

    services save(services service);

    void delete(services service);
}
