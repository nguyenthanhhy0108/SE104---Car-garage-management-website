package com.example.se.DAO;

import com.example.se.model.users;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface usersDAO{
    List<users> findByUsername(String username);

    users save(users User);
}
