package com.example.se.DAO;

import com.example.se.model.user_details;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface user_detailsDAO {
    List<user_details> findByEmail(String email);
    user_details save(user_details user_details);
}
