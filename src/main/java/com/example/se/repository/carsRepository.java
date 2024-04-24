package com.example.se.repository;

import com.example.se.model.cars;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface carsRepository extends CrudRepository<cars, Integer> {
}
