package com.example.se.service.impl;

import com.example.se.repository.userDetailsRepository;
import com.example.se.model.userDetails;
import com.example.se.service.userDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userDetailsServiceImpl implements userDetailsService {

    @Override
    public void delete(userDetails userDetails) {
        this.userDetailsRepository.delete(userDetails);
    }

    //Define and initialize internal attribute (DAO layer)
    private final userDetailsRepository userDetailsRepository;

    /**
     * Dependency Injection
     * @param userDetailsRepository: userDetailsRepository object
     */
    @Autowired
    public userDetailsServiceImpl(userDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    /**
     * Use DAO attribute to get list user_details from database
     * @param username: Provided username
     * @return
     * A list of userDetails objects
     */
    @Override
    public List<userDetails> findByUsername(String username) {
        return this.userDetailsRepository.findByUsername(username);
    }

    /**
     * Use DAO attribute to get list user_details from database
     * @param email: Provided email (String)
     * @return
     * A list of userDetails objects
     */
    @Override
    public List<userDetails> findByEmail(String email) {
        return userDetailsRepository.findByEmail(email);
    }

    /**
     * Use DAO attribute to save user_details on database
     * @param userDetails: userDetails object
     * @return
     * userDetails object which is saved
     */
    @Override
    public userDetails save(userDetails userDetails) {
        return userDetailsRepository.save(userDetails);
    }

    /**
     * Use DAO attribute to check provided email with provided username
     * @param email: Provided email (String)
     * @param username: Provided username (String)
     * @return
     * A boolean
     */
    @Override
    public boolean checkEmailMatchUsername(String email, String username) {
        String real_email = userDetailsRepository.findByUsername(username).get(0).getEmail();
        return real_email.equals(email);
    }

    /**
     * Use DAO attribute to check provided email exist in database
     * @param email: Provided email (String)
     * @return
     * A boolean
     */
    @Override
    public boolean checkEmailExists(String email) {
        if(userDetailsRepository.findByEmail(email).isEmpty())
            return false;
        return true;
    }
}
