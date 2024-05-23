package com.example.se.service.impl;

import com.example.se.model.receipts;
import com.example.se.repository.receiptsRepository;
import com.example.se.service.receiptsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class receiptsServiceImpl implements receiptsService {
    private final receiptsRepository receiptsRepository;

    public receiptsServiceImpl(receiptsRepository receiptsRepository) {
        this.receiptsRepository = receiptsRepository;
    }

    @Override
    public receipts findByOrdernumber(int orderNumber) {
        return this.receiptsRepository.findByOrdernumber(orderNumber);
    }

    @Override
    public List<receipts> findAllReceipts() {
        return this.receiptsRepository.findAll();
    }

    @Transactional
    @Override
    public receipts save(receipts receipt) {
        return this.receiptsRepository.save(receipt);
    }

    @Transactional
    @Override
    public void deleteById(int ordernumber) {
        this.receiptsRepository.deleteByOrdernumber(ordernumber);
    }

    /**
     * Implement find all car ID
     * @return
     * List carID
     */
    @Override
    public List<Integer> findAllCarID() {
        return this.receiptsRepository.findAllCarID();
    }

    /**
     * Implement find all by car ID
     * @param carId: int
     * @return
     * List of receipts
     */
    @Override
    public List<LocalDate> findAllDatesByCarId(int carId) {
        return this.receiptsRepository.findAllDatesByCarId(carId);
    }

    /**
     * Implement find all by car ID and date
     * @param carId: int
     * @param date: LocalDate
     * @return
     * List order numbers
     */
    @Override
    public List<Integer> findAllOrderIDByCarIdAndDate(int carId, LocalDate date) {
        return this.receiptsRepository.findAllOrderIDByCarIdAndDate(carId, date);
    }

    @Override
    public List<receipts> findAllReceiptsByVeID(int carId) {
        return this.receiptsRepository.findAllReceiptsByVeID(carId);
    }
}
