package com.example.se.service;

import com.example.se.model.receipts;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface receiptsService {
    receipts findByOrdernumber(int orderNumber);

    List<receipts> findAllReceipts();
    receipts save(receipts receipt);
    void deleteById(int receiptId);

    /**
     * Find all carID
     * @return
     * List carID
     */
    List<Integer> findAllCarID();

    /**
     * Find all receipts by carID
     * @param carId: int
     * @return
     * List of receipts
     */
    List<LocalDate> findAllDatesByCarId(int carId);

    List<Integer> findAllOrderIDByCarIdAndDate(int carId, LocalDate date);

    List<receipts> findAllReceiptsByVeID(int carId);

    /**
     * Find receipt by car ID
     * @param carId: int
     * @return
     * receipts object
     */
    List<receipts> findByCarId(int carId);

    double getTotalDebtOfCarId(int carId);

    String countDistinctByMonthAndYearAndBrandID(@Param("month") int month, @Param("year") int year, @Param("brandID") int brand);

    double getValueOfReceipt(int receiptId);

    List<receipts> getByMonthAndYearAndBrandID(@Param("month") int month, @Param("year") int year, @Param("brandID") int brandID);

    List<receipts> getByMonthAndYear(@Param("month") int month, @Param("year") int year);
}
