package com.example.se.Repository;

import com.example.se.model.authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface authoritiesRepository extends JpaRepository<authorities, String> {
    List<authorities> findByUsername(String username);
}
