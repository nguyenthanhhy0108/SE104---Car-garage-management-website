package com.example.se.service;

import com.example.se.model.authorities;

import java.util.List;

//Define methods for Service layer which is got by Controller
public interface authoritiesService {
    List<authorities> findByUsername(String username);
    authorities save(authorities Authorities);
    void delete(authorities authorities);
}
