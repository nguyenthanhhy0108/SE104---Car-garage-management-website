package com.example.se.service;

import com.example.se.repository.usersRepository;
import com.example.se.model.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class usersServiceImpl implements usersService{

    //Define and initialize internal attribute (DAO layer)
    private final usersRepository usersRepository;
    @Autowired
    public usersServiceImpl(usersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    //Use DAO attribute to get list users from database
    @Override
    public List<users> findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    //Use DAO attribute to save users on database
    public users save(users User) {
        return usersRepository.save(User);
    }
}
