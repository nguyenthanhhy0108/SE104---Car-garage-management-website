package com.example.se.repository;

import com.example.se.model.maintenanceRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface maintenanceRecordsRepository extends JpaRepository<maintenanceRecords, Integer> {
}
