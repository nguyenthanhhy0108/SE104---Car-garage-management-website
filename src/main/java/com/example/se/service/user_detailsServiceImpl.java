package com.example.se.service;

import com.example.se.DAO.user_detailsDAO;
import com.example.se.model.user_details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class user_detailsServiceImpl implements user_detailsService {
    private final user_detailsDAO user_detailsDAO;
    @Autowired
    public user_detailsServiceImpl(user_detailsDAO user_detailsDAO) {
        this.user_detailsDAO = user_detailsDAO;
    }

    @Override
    public List<user_details> findByEmail(String email) {
        return user_detailsDAO.findByEmail(email);
    }
    @Transactional
    @Override
    public user_details save(user_details user_details) {
        return user_detailsDAO.save(user_details);
    }
}
