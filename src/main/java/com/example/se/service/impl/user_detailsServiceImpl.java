package com.example.se.service.impl;

import com.example.se.repository.user_detailsRepository;
import com.example.se.model.user_details;
import com.example.se.service.user_detailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class user_detailsServiceImpl implements user_detailsService {
    //Define and initialize internal attribute (DAO layer)
    private final user_detailsRepository user_detailsRepository;
    @Autowired
    public user_detailsServiceImpl(user_detailsRepository user_detailsRepository) {
        this.user_detailsRepository = user_detailsRepository;
    }
    //Use DAO attribute to get list user_details from database
    @Override
    public List<user_details> findByUsername(String username) {
        return this.user_detailsRepository.findByUsername(username);
    }

    //Use DAO attribute to get list user_details from database
    @Override
    public List<user_details> findByEmail(String email) {
        return user_detailsRepository.findByEmail(email);
    }

    //Use DAO attribute to save user_details on database
    @Override
    public user_details save(user_details user_details) {
        return user_detailsRepository.save(user_details);
    }
}
