package com.example.se.service;

import com.example.se.DAO.userDetailsDAO;
import com.example.se.model.userDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userDetailsServiceImpl implements userDetailsService{
    private final userDetailsDAO UserDetailsDAO;
    @Autowired
    public userDetailsServiceImpl(userDetailsDAO userDetailsDAO) {
        this.UserDetailsDAO = userDetailsDAO;
    }
    @Override
    public List<userDetails> findByEmail(String email) {
        return UserDetailsDAO.findByEmail(email);
    }
}
