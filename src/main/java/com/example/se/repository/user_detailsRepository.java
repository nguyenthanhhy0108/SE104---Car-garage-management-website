package com.example.se.repository;

import com.example.se.model.user_details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Interface in DAO layers used in basic action: add, delete, save
//Define finByEmail method to search and return a list of user_details which have the similar email
@Repository
public interface user_detailsRepository extends JpaRepository<user_details, String> {
    List<user_details> findByEmail(String email);
}
