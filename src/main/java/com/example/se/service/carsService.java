package com.example.se.service;

import com.example.se.model.cars;
import com.example.se.model.dataDTO.CarDTO;

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

    /**
     * Find by Owner ID
     * @param ownerID: int
     * @return
     * List cars objects
     */
    List<cars> findByOwnerID(int ownerID);

    /**
     * Convert cars object to CarDTO
     * @param cars: original cars object
     * @return
     * CarDTO object
     */
    CarDTO toDTO(cars cars);

    /**
     * Convert cars object to CarDTO
     * @param cars: original cars object
     * @return
     * CarDTO object
     */
    List<CarDTO> toDTO(List<cars> cars);
}
