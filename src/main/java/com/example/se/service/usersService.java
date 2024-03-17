package com.example.se.service;

import com.example.se.model.users;

import java.util.List;

//Define methods for Service layer which is got by Controller
public interface usersService {
    List<users> findByUsername(String username);
    users save(users User);
    users updatePasswordByUsername(String username, String newPassword);
}
