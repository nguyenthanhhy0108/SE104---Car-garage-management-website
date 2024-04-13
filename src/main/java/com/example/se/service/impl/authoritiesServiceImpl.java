package com.example.se.service.impl;

import com.example.se.repository.authoritiesRepository;
import com.example.se.model.authorities;
import com.example.se.service.authoritiesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class authoritiesServiceImpl implements authoritiesService {
    //Define and initialize internal attribute (DAO layer)
    private final authoritiesRepository AuthoritiesRepository;

    /**
     * Dependency Injection
     * @param AuthoritiesRepository: authoritiesRepository object (DAO)
     */
    @Autowired
    public authoritiesServiceImpl(authoritiesRepository AuthoritiesRepository) {
        this.AuthoritiesRepository = AuthoritiesRepository;
    }

    /**
     * Use DAO attribute to get list authorities from database
     * @param username: Provided username (String)
     * @return
     * A list of authorities objects
     */
    @Override
    public List<authorities> findByUsername(String username) {
        return AuthoritiesRepository.findByUsername(username);
    }

    /**
     * Use DAO attribute to save authorities on database
     * @param Authorities: authorities object
     * @return
     * An authorities which is saved
     */
    @Override
    public authorities save(authorities Authorities) {
        return AuthoritiesRepository.save(Authorities);
    }

    /**
     * Use DAO attribute to delete authorities in database
     * @param authorities: authorities object
     */
    @Override
    public void delete(authorities authorities) {
        this.AuthoritiesRepository.delete(authorities);
    }
}
