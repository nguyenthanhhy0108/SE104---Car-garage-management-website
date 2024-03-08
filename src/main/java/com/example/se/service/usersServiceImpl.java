package com.example.se.service;

import com.example.se.Repository.usersRepository;
import com.example.se.model.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class usersServiceImpl implements usersService{
    private final usersRepository usersRepository;
    @Autowired
    public usersServiceImpl(usersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<users> findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    public users save(users User) {
        return usersRepository.save(User);
    }
}
