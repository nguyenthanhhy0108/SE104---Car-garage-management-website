package com.example.se.service;

import com.example.se.Repository.authoritiesRepository;
import com.example.se.model.authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class authoritiesServiceImpl implements authoritiesService{
    private final authoritiesRepository AuthoritiesRepository;
    @Autowired
    public authoritiesServiceImpl(authoritiesRepository AuthoritiesRepository) {
        this.AuthoritiesRepository = AuthoritiesRepository;
    }

    @Override
    public List<authorities> findByUsername(String username) {
        return AuthoritiesRepository.findByUsername(username);
    }

    public authorities save(authorities Authorities) {
        return AuthoritiesRepository.save(Authorities);
    }
}
