package com.example.se.service;

import com.example.se.model.user_details;

import java.util.List;

//Define methods for Service layer which is got by Controller
public interface user_detailsService {
    List<user_details> findByEmail(String email);
    List<user_details> findByUsername(String username);
    user_details save(user_details user_details);
    void delete(user_details userDetails);
}
