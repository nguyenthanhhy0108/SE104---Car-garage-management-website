package com.example.se.service;

import com.example.se.model.userDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface userDetailsService {
    List<userDetails> findByEmail(String email);
}
