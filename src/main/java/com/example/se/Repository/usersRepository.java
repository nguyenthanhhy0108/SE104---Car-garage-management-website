package com.example.se.Repository;

import com.example.se.model.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface usersRepository extends JpaRepository<users, String> {
    List<users> findByUsername(String username);
}
