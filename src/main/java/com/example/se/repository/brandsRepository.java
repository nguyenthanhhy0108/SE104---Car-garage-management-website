package com.example.se.repository;

import com.example.se.model.brands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface brandsRepository extends JpaRepository<brands, Integer> {

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

    @Query("SELECT DISTINCT B FROM BRANDS B")
    List<brands> findDistinctAll();
}
