package com.example.se.service.impl;

import com.example.se.model.receipts;
import com.example.se.service.receiptsService;
import org.springframework.stereotype.Service;
import com.example.se.repository.receiptsRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public class receiptsServiceImpl implements receiptsService {
    private final receiptsRepository receiptsRepository;

    public receiptsServiceImpl(com.example.se.repository.receiptsRepository receiptsRepository) {
        this.receiptsRepository = receiptsRepository;
    }

    @Override
    public List<receipts> findAllreceipts() {
        return this.receiptsRepository.findAll();
    }

    @Transactional
    @Override
    public receipts save(receipts receipts) {
        return this.receiptsRepository.save(receipts);
    }
    @Override
    public receipts findReceiptsbyId(int receiptId) {
        return this.receiptsRepository.findByReceiptsId(receiptId);
    }

    @Transactional
    @Override
    public void deletebyId(int receiptId) {
        this.receiptsRepository.deleteById(receiptId);
    }
}
