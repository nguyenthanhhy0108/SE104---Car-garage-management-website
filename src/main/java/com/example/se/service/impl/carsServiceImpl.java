package com.example.se.service.impl;

import com.example.se.model.cars;
import com.example.se.model.dataDTO.CarDTO;
import com.example.se.repository.carsRepository;
import com.example.se.service.brandsService;
import com.example.se.service.carsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class carsServiceImpl implements carsService {

    private final carsRepository carsRepository;
    private final brandsService brandsService;

    /**
     * Dependency Injection
     * @param carsRepository: carsRepository object
     * @param brandsService: brandsService object
     */
    @Autowired
    public carsServiceImpl(carsRepository carsRepository,
                           brandsService brandsService) {
        this.carsRepository = carsRepository;
        this.brandsService = brandsService;
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

    /**
     * Implement delete by car id
     * @param carId: int
     */
    @Transactional
    @Override
    public void deleteByCarID(int carId) {
        this.carsRepository.deleteByCarID(carId);
    }

    /**
     * Implement find by owner ID
     * @param ownerID: int
     * @return
     * List cars objects
     */
    @Override
    public List<cars> findByOwnerID(int ownerID) {
        return this.carsRepository.findByOwnerID(ownerID);
    }

    /**
     * Implement method convert to car DTO
     * @param cars: original cars object
     * @return
     * CarDTO object
     */
    @Override
    public CarDTO toDTO(cars cars) {
        CarDTO carDTO = new CarDTO();
        carDTO.setLicenseNumber(cars.getLicensePlate());
        carDTO.setBrand(this.brandsService.findByBrandID(cars.getBrandID()).getBrandName());

        return carDTO;
    }

    /**
     * Implement to DTO list
     * @param cars: original list cars objects
     * @return
     * List car DTO objects
     */
    @Override
    public List<CarDTO> toDTO(List<cars> cars) {
        List<CarDTO> carDTOList = new ArrayList<>();
        for(cars car : cars) {
            carDTOList.add(toDTO(car));
        }
        return carDTOList;
    }
}
