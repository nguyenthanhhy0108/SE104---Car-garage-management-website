package com.example.se.service.impl;

import com.example.se.repository.usersRepository;
import com.example.se.model.users;
import com.example.se.service.usersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class usersServiceImpl implements usersService {

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
    @Override
    public users save(users User) {
        return usersRepository.save(User);
    }

    //Use DAO attribute to save users on database
    @Override
    public users updatePasswordByUsername(String username, String newPassword) {
        users new_user = usersRepository.findByUsername(username).get(0);
        new_user.setPassword(newPassword);
        usersRepository.save(new_user);
        return new_user;
    }
}
