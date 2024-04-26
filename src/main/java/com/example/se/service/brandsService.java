package com.example.se.service;

import com.example.se.model.brands;

public interface brandsService {
    /**
     * Find brand by brand ID
     * @param brandID: int
     * @return
     * brands object
     */
    brands findByBrandID(int brandID);

    /**
     * Find brand by brand name
     * @param brandName: String
     * @return
     * brands object
     */
    brands findByBrandName(String brandName);

    /**
     * Save method
     * @param brands: brands object
     * @return
     * brands object which was saved
     */
    brands save(brands brands);
}
