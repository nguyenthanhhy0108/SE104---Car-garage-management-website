package com.example.se.repository;

import com.example.se.model.receipts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

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

    @Query("SELECT R FROM REPAIR_ORDERS R WHERE R.carId = :carId ")
    List<receipts> findAllReceiptsByVeID(int carId);

    /**
     * Find receipt by car ID
     * @param carId: int
     * @return
     * receipts object
     */
    List<receipts> findByCarId(int carId);

    @Query("SELECT COUNT(ro) FROM REPAIR_ORDERS ro JOIN CARS c ON ro.carId = c.carID WHERE FUNCTION('MONTH', ro.date) = :month AND FUNCTION('YEAR', ro.date) = :year AND c .brandID = :brandID")
    String countDistinctByMonthAndYearAndBrandID(@Param("month") int month, @Param("year") int year, @Param("brandID") int brandID);

    @Query("SELECT ro FROM REPAIR_ORDERS ro JOIN CARS c ON ro.carId = c.carID WHERE FUNCTION('MONTH', ro.date) = :month AND FUNCTION('YEAR', ro.date) = :year AND c .brandID = :brandID")
    List<receipts> getByMonthAndYearAndBrandID(@Param("month") int month, @Param("year") int year, @Param("brandID") int brandID);

    @Query("SELECT ro FROM REPAIR_ORDERS ro WHERE FUNCTION('MONTH', ro.date) = :month AND FUNCTION('YEAR', ro.date) = :year")
    List<receipts> getByMonthAndYear(@Param("month") int month, @Param("year") int year);
}
