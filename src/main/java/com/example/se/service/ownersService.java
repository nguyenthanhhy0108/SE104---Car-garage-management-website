package com.example.se.service;

import com.example.se.model.owners;

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
     * Save owners to database
     * @param owners owners object
     * @return
     * owners object which was saved
     */
    owners save(owners owners);
}

