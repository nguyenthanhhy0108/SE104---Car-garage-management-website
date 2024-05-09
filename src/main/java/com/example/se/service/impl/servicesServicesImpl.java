package com.example.se.service.impl;

import com.example.se.model.services;
import com.example.se.repository.servicesRepository;
import com.example.se.service.servicesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class servicesServicesImpl implements servicesServices {

    private final servicesRepository servicesRepository;

    /**
     * Dependency Injection
     * @param servicesRepository: servicesRepository object
     */
    @Autowired
    public servicesServicesImpl(com.example.se.repository.servicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    /**
     * Implement find all services method
     * @return
     * List services objects
     *
     */
    @Override
    public List<services> findAll() {
        return this.servicesRepository.findAll();
    }

    /**
     * Implement find by ID
     * @param id: int
     * @return
     * Service object
     */
    @Override
    public services findByServicesID(int id) {
        return this.servicesRepository.findByServicesID(id);
    }
}
