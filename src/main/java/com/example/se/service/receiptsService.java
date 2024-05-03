package com.example.se.service;

import com.example.se.model.receipts;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface receiptsService {
    receipts findByOrdernumber(int orderNumber);

    List<receipts> findAllReceipts();
    receipts save(receipts receipt);
    void deleteById(int receiptId);

}
