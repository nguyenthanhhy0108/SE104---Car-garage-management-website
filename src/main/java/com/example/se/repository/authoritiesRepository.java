package com.example.se.repository;

import com.example.se.model.authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Interface in DAO layers used in basic action: add, delete, save
//Define finByUsername method to search and return a list of authorities which have the similar username
@Repository
public interface authoritiesRepository extends JpaRepository<authorities, String> {
    List<authorities> findByUsername(String username);
}
