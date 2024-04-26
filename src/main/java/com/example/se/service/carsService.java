package com.example.se.service;

import com.example.se.model.cars;

import java.util.List;

public interface carsService {
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

    /**
     * Save method
     * @param cars: cars object
     * @return
     * Cars which was saved
     */
    cars save(cars cars);

    /**
     * Delete by car id
     * @param carId: int
     */
    void deleteByCarID(int carId);
}
