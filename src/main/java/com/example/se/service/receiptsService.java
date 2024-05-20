package com.example.se.service;

import com.example.se.model.dataDTO.BrandIncomeDTO;
import com.example.se.model.receipts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
     List<BrandIncomeDTO> findTotalIncomeByBrandForMonth(int month);
    List<Integer> findAllCarIDByMonth(int month);

}
