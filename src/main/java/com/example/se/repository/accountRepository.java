package com.example.se.repository;

import com.example.se.model.account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface accountRepository extends CrudRepository<account, String> {
    List<account>findByUsername(String username);
}
