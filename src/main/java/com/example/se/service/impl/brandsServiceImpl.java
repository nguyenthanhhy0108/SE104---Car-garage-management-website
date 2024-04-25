package com.example.se.service.impl;

import com.example.se.model.brands;
import com.example.se.repository.brandsRepository;
import com.example.se.service.brandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class brandsServiceImpl implements brandsService {

    private final brandsRepository brandsRepository;

    /**
     * Dependency Injection
     * @param brandsRepository: brandsRepository obejct
     */
    @Autowired
    public brandsServiceImpl(brandsRepository brandsRepository) {
        this.brandsRepository = brandsRepository;
    }

    /**
     * Implement find by brand ID method
     * @param brandID: int
     * @return
     * brands object
     */
    @Override
    public brands findByBrandID(int brandID) {
        return this.brandsRepository.findByBrandID(brandID);
    }

    @Override
    public brands findByBrandName(String brandName) {
        return this.brandsRepository.findByBrandName(brandName);
    }

    /**
     * Implement save method
     * @param brands: brands object
     * @return
     * brand object which was saved
     */
    @Override
    public brands save(brands brands) {
        return this.brandsRepository.save(brands);
    }
}
