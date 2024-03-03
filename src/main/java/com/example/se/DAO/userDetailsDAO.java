package com.example.se.DAO;

import com.example.se.model.userDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userDetailsDAO {
    List<userDetails> findByEmail(String email);
}
