package com.example.se.repository;

import com.example.se.model.parts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface partsRepository extends JpaRepository<parts, Integer> {
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
}
