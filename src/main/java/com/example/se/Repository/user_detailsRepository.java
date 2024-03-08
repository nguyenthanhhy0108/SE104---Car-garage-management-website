package com.example.se.Repository;

import com.example.se.model.user_details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface user_detailsRepository extends JpaRepository<user_details, String> {
    List<user_details> findByEmail(String email);
}
