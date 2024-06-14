package com.example.se.service.impl;

import com.example.se.model.receipts;
import com.example.se.repository.receiptsRepository;
import com.example.se.service.receiptsService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class receiptsServiceImpl implements receiptsService {
    private final receiptsRepository receiptsRepository;
    private final EntityManager entityManager;

    @Autowired
    public receiptsServiceImpl(receiptsRepository receiptsRepository, EntityManager entityManager) {
        this.receiptsRepository = receiptsRepository;
        this.entityManager = entityManager;
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

    /**
     * Find receipts by car ID
     * @param carId: int
     * @return receipts object
     */
    @Override
    public List<receipts> findByCarId(int carId) {
        return this.receiptsRepository.findByCarId(carId);
    }

    @Override
    public double getTotalDebtOfCarId(int carId) {
        List<receipts> list = this.findByCarId(carId);
        double total = 0.0;
        for (receipts receipt : list) {
            total += receipt.getAmountOwed();
        }
        return total;
    }

    @Override
    public String countDistinctByMonthAndYearAndBrandID(int month, int year, int brandID) {
        return this.receiptsRepository.countDistinctByMonthAndYearAndBrandID(month, year, brandID);
    }

    @Override
    public double getValueOfReceipt(int receiptId) {
        receipts receipt = this.receiptsRepository.findByOrdernumber(receiptId);
        return receipt.getAmountpaid();
    }

    @Override
    public List<receipts> getByMonthAndYearAndBrandID(int month, int year, int brandID) {
        return this.receiptsRepository.getByMonthAndYearAndBrandID(month, year, brandID);
    }

    @Override
    public List<receipts> getByMonthAndYear(int month, int year) {
        return this.receiptsRepository.getByMonthAndYear(month, year);
    }

    @Transactional
    @Override
    public receipts update(receipts receipts) {
        return this.entityManager.merge(receipts);
    }

    @Transactional
    @Override
    public void delete(receipts receipts) {
        this.receiptsRepository.delete(receipts);
    }

}
