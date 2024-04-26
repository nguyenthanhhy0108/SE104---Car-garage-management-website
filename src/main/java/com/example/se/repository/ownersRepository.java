package com.example.se.repository;

import com.example.se.model.owners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ownersRepository extends JpaRepository<owners, Integer> {
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
     * Delete by owner ID
     * @param ownerID: int
     */
    void deleteByOwnerID(int ownerID);
}
