package com.example.se.service.impl;

import com.example.se.model.cars;
import com.example.se.repository.carsRepository;
import com.example.se.service.carsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class carsServiceImpl implements carsService {

    private final carsRepository carsRepository;

    /**
     * Dependency Injection
     * @param carsRepository: carsRepository object
     */
    @Autowired
    public carsServiceImpl(carsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    /**
     * Implement find by car ID method
     * @param carId: int
     * @return
     * cars object
     */
    @Override
    public cars findByCarID(int carId) {
        return this.carsRepository.findByCarID(carId);
    }

    /**
     * Implement find by license plate method
     * @param licensePlate: String
     * @return
     * A List of cars object
     */
    @Override
    public List<cars> findByLicensePlate(String licensePlate) {
        return this.carsRepository.findByLicensePlate(licensePlate);
    }

    /**
     * Implement save method
     * @param cars: cars object
     * @return
     * cars object which was saved
     */
    @Override
    public cars save(cars cars) {
        return this.carsRepository.save(cars);
    }
}
