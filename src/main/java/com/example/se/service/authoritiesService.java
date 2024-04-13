package com.example.se.service;

import com.example.se.model.authorities;

import java.util.List;

//Define methods for Service layer which is got by Controller
public interface authoritiesService {
    /**
     * Define finByUsername service to search and return a list of authorities which have the similar username
     * @param username: Provided username (String)
     * @return
     * List of authorities objects
     */
    List<authorities> findByUsername(String username);

    /**
     * Define save service to save an authorities object to database
     * @param Authorities: authorities object
     * @return
     * authorities object which is saved
     */
    authorities save(authorities Authorities);

    /**
     * Define delete service to delete an authorities object in database
     * @param authorities: authorities object
     */
    void delete(authorities authorities);
}
