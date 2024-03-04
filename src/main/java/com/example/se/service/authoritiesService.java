package com.example.se.service;

import com.example.se.model.authorities;

import java.util.List;

public interface authoritiesService {
    List<authorities> findByUsername(String username);
    authorities save(authorities Authorities);
}
