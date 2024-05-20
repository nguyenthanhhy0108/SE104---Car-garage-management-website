package com.example.se.repository;

import com.example.se.model.dataDTO.BrandIncomeDTO;
import com.example.se.model.receipts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface receiptsRepository extends JpaRepository<receipts, Integer> {
    void deleteByOrdernumber(int orderNumber);
    receipts findByOrdernumber(int orderNumber);

    /**
     * Find all carID
     * @return
     * List carID
     */
    @Query("SELECT DISTINCT R.carId FROM REPAIR_ORDERS R")
    List<Integer> findAllCarID();

    /**
     * Find all receipts by carID
     * @param carId: int
     * @return
     * List of receipts
     */
    @Query("SELECT DISTINCT R.date FROM REPAIR_ORDERS R WHERE R.carId = :carId")
    List<LocalDate> findAllDatesByCarId(int carId);

    @Query("SELECT R.ordernumber FROM REPAIR_ORDERS R WHERE R.carId = :carId AND R.date = :date")
    List<Integer> findAllOrderIDByCarIdAndDate(int carId, LocalDate date);
    @Query("SELECT DISTINCT ro.carId FROM REPAIR_ORDERS ro WHERE month(ro.date) = :month")
    List<Integer> findAllCarIDByMonth(int month);
    @Query("SELECT ro.cars.brands, SUM(ro.amountpaid) " +
            "FROM REPAIR_ORDERS ro " +
            "WHERE MONTH(ro.date) = :month " +
            "GROUP BY ro.cars.brands")
    List<BrandIncomeDTO> findTotalIncomeByBrandForMonth(@Param("month") int month);

}
