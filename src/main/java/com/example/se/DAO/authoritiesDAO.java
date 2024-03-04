package com.example.se.DAO;

import com.example.se.model.authorities;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface authoritiesDAO {
    List<authorities> findByUsername(String username);
    authorities save(authorities Authorities);
}
