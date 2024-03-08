package com.example.se.service;

import com.example.se.Repository.user_detailsRepository;
import com.example.se.model.user_details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class user_detailsServiceImpl implements user_detailsService {
    private final user_detailsRepository user_detailsRepository;
    @Autowired
    public user_detailsServiceImpl(user_detailsRepository user_detailsRepository) {
        this.user_detailsRepository = user_detailsRepository;
    }

    @Override
    public List<user_details> findByEmail(String email) {
        return user_detailsRepository.findByEmail(email);
    }

    public user_details save(user_details user_details) {
        return user_detailsRepository.save(user_details);
    }
}
