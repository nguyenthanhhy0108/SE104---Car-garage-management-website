package com.example.se.repository;

import com.example.se.model.owners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ownersRepository extends JpaRepository<owners, Integer> {
}
