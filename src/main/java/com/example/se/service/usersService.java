package com.example.se.service;

import com.example.se.model.users;

import java.util.List;

public interface usersService {
    List<users> findByUsername(String username);

    users save(users User);
}
