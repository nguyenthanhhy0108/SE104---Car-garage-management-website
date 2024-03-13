package com.example.se.service.impl;

import com.example.se.repository.authoritiesRepository;
import com.example.se.model.authorities;
import com.example.se.service.authoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class authoritiesServiceImpl implements authoritiesService {
    //Define and initialize internal attribute (DAO layer)
    private final authoritiesRepository AuthoritiesRepository;
    @Autowired
    public authoritiesServiceImpl(authoritiesRepository AuthoritiesRepository) {
        this.AuthoritiesRepository = AuthoritiesRepository;
    }

    //Use DAO attribute to get list authorities from database
    @Override
    public List<authorities> findByUsername(String username) {
        return AuthoritiesRepository.findByUsername(username);
    }

    //Use DAO attribute to save authorities on database
    public authorities save(authorities Authorities) {
        return AuthoritiesRepository.save(Authorities);
    }
}
