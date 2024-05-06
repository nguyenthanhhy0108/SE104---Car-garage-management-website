package com.example.se.service;

import com.example.se.model.parts;

import java.util.List;

public interface partsService {
    /**
     * Find by name
     * @param name: String
     * @return
     * parts object
     */
    parts findByName(String name);

    /**
     * Find by id
     * @param id: int
     * @return
     * parts object
     */
    parts findByPartID(int id);

    /**
     * Find all parts
     * @return
     * List of parts objects
     */
    List<parts> findAll();
}
