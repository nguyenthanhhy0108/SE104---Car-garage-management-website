package com.example.se.service;

import com.example.se.DAO.usersDAO;
import com.example.se.model.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class usersServiceImpl implements usersService{
    private final usersDAO UsersDAO;
    @Autowired
    public usersServiceImpl(usersDAO usersDAO) {
        UsersDAO = usersDAO;
    }

    @Override
    public List<users> findByUsername(String username) {
        return UsersDAO.findByUsername(username);
    }

    @Transactional
    @Override
    public users save(users User) {
        return UsersDAO.save(User);
    }
}
