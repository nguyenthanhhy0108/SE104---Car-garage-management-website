package com.example.se.repository;

import com.example.se.model.maintenanceRecords;
import com.example.se.model.receipts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface receiptsRepository extends JpaRepository<receipts, Integer> {
    void deleteById(int receiptsId);
    receipts findByReceiptsId(int receiptsId);
}
