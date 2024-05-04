package com.example.se.service;

import com.example.se.model.maintenanceRecords;

import java.util.List;

public interface maintenanceRecordsService {
    /**
     * Find all records
     * @return
     * A list containing all records
     */
    List<maintenanceRecords> findAllRecords();

    /**
     * Save method
     * @param maintenanceRecords maintenanceRecords
     * @return
     * maintenanceRecords object which was saved
     */
    maintenanceRecords save(maintenanceRecords maintenanceRecords);

    /**
     * Find by record ID
     * @param id: int
     * @return
     * maintenanceRecords object
     */
    maintenanceRecords findByRecordID(int id);

    /**
     * Delete by id
     * @param id: int
     */
    void deleteByRecordID(int id);

    /**
     * Find by car ID
     * @param carID: int
     * @return
     * maintenanceRecord object
     */
    maintenanceRecords findByCarID(int carID);
}
