package com.example.se.repository;

import com.example.se.model.maintenanceRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface maintenanceRecordsRepository extends JpaRepository<maintenanceRecords, Integer> {

    /**
     * Delete by id
     * @param id: int
     */
    void deleteByRecordID(int id);

    /**
     * Find by record ID
     * @param id: int
     * @return
     * maintenanceRecords object
     */
    maintenanceRecords findByRecordID(int id);
}
