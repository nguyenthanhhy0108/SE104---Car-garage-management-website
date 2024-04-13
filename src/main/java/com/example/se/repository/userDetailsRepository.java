package com.example.se.repository;

import com.example.se.model.userDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Interface in DAO layers used in basic action: add, delete, save
@Repository
public interface userDetailsRepository extends JpaRepository<userDetails, String> {
    /**
     * Define finByEmail method to search and return a list of user_details which have the similar email
     * @param email: Provided email (String)
     * @return
     * List of userDetails objects
     */
    List<userDetails> findByEmail(String email);

    /**
     * Define finByUsername method to search and return a list of user_details which have the similar username
     * @param username: Provided username
     * @return
     * List of userDetails objects
     */
    List<userDetails> findByUsername(String username);
}
