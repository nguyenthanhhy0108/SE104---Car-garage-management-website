package com.example.se.DAO;

import com.example.se.model.users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface usersDAO{
    List<users>findByUsername(String username);
}
