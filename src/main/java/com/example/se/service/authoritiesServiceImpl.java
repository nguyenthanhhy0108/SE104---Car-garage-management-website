package com.example.se.service;

import com.example.se.DAO.authoritiesDAO;
import com.example.se.model.authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class authoritiesServiceImpl implements authoritiesService{
    private final authoritiesDAO AuthoritiesDAO;
    @Autowired
    public authoritiesServiceImpl(authoritiesDAO authoritiesDAO) {
        AuthoritiesDAO = authoritiesDAO;
    }

    @Override
    public List<authorities> findByUsername(String username) {
        return AuthoritiesDAO.findByUsername(username);
    }
    @Transactional
    @Override
    public authorities save(authorities Authorities) {
        return AuthoritiesDAO.save(Authorities);
    }
}
