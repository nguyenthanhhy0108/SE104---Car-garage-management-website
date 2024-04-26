package com.example.se.repository;

import com.example.se.model.cars;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface carsRepository extends CrudRepository<cars, Integer> {

    /**
     * Find by car ID
     * @param carId: int
     * @return
     * cars object
     */
    cars findByCarID(int carId);

    /**
     * Find by License Plate
     * @param licensePlate: String
     * @return
     * A list of cars object
     */
    List<cars> findByLicensePlate(String licensePlate);
}
