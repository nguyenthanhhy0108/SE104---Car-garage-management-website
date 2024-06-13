package com.example.se.repository;

import com.example.se.model.TriggerValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface triggerValueRepository extends JpaRepository<TriggerValue, Long> {
    TriggerValue findByName(String name);
}
