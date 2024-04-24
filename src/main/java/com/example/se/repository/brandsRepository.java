package com.example.se.repository;

import com.example.se.model.brands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface brandsRepository extends JpaRepository<brands, Integer> {
}
