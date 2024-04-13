package com.example.se.service;

import com.example.se.model.userDetails;

import java.util.List;

//Define methods for Service layer which is got by Controller
public interface userDetailsService {
    /**
     * Define finByEmail service to search and return a list of user_details which have the similar email
     * @param email: Provided email (String)
     * @return
     * List of userDetails objects
     */
    List<userDetails> findByEmail(String email);

    /**
     * Define finByUsername service to search and return a list of user_details which have the similar username
     * @param username: Provided username
     * @return
     * List of userDetails objects
     */
    List<userDetails> findByUsername(String username);

    /**
     * Define save service to save a new userDetails object to database
     * @param userDetails: userDetails object
     * @return
     * userDetails object which is saved
     */
    userDetails save(userDetails userDetails);

    /**
     * Define save service to delete an userDetails object in database
     * @param userDetails: userDetails object
     */
    void delete(userDetails userDetails);

    /**
     * Define checkEmailMatchUsername service to check provided email with provided username
     * @param email: Provided email (String)
     * @param username: Provided username (String)
     * @return
     * A boolean
     */
    boolean checkEmailMatchUsername(String email, String username);

    /**
     * Define checkEmailMatchUsername service to check provided email exist
     * @param email: Provided email (String)
     * @return
     * A boolean
     */
    boolean checkEmailExists(String email);
}
