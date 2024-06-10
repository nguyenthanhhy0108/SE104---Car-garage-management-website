package com.example.se.service.impl;

import com.example.se.model.parts;
import com.example.se.repository.partsRepository;
import com.example.se.service.partsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class partsServiceImpl implements partsService {

    private final partsRepository partsRepository;

    /**
     * Dependency Injection
     * @param partsRepository: partsRepository object
     */
    @Autowired
    public partsServiceImpl(partsRepository partsRepository) {
        this.partsRepository = partsRepository;
    }

    /**
     * Implement find by name
     * @param name: String
     * @return
     * parts object
     */
    @Override
    public parts findByName(String name) {
        return this.partsRepository.findByName(name);
    }

    /**
     * Implement find by id
     * @param id: int
     * @return
     * parts object
     */
    @Override
    public parts findByPartID(int id) {
        return this.partsRepository.findByPartID(id);
    }

    /**
     * Implement find all
     * @return
     * List of parts objects
     */
    @Override
    public List<parts> findAll() {
        return this.partsRepository.findAll();
    }

    @Override
    public parts save(parts parts) {
        return this.partsRepository.save(parts);
    }

    @Override
    public void delete(parts parts) {
        this.partsRepository.delete(parts);
    }
}
