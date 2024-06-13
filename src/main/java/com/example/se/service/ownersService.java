package com.example.se.service;

import com.example.se.model.owners;

import java.util.List;

public interface ownersService {
    /**
     * Find owner by owner ID
     * @param ownerID: int
     * @return
     * owners object
     */
    owners findByOwnerID(int ownerID);

    /**
     * Find owner by username
     * @param username: String
     * @return
     * owners object
     */
    owners findByUsername(String username);

    /**
     * Find owner by email
     * @param email: String
     * @return
     * owners object
     */
    owners findByOwnerEmail(String email);

    /**
     * Save owners to database
     * @param owners owners object
     * @return
     * owners object which was saved
     */
    owners save(owners owners);

    /**
     * Delete by owner ID
     * @param ownerID: int
     */
    void deleteByOwnerID(int ownerID);

    /**
     * Find owner by phone number
     * @param phoneNumber: String
     * @return
     * owner object
     */
    owners findByOwnerPhoneNumber(String phoneNumber);

    /**
     * Find all owners
     * @return
     * List of owners
     */
    List<owners> findAll();
}

