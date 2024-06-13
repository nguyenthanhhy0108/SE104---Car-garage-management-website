package com.example.se.service.impl;

import com.example.se.model.TriggerValue;
import com.example.se.repository.triggerValueRepository;
import com.example.se.service.triggerValueServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class triggerValueServicesImpl implements triggerValueServices {
    private final triggerValueRepository triggerValueRepository;

    @Autowired
    public triggerValueServicesImpl(com.example.se.repository.triggerValueRepository triggerValueRepository) {
        this.triggerValueRepository = triggerValueRepository;
    }

    @Override
    public TriggerValue findByName(String name) {
        return this.triggerValueRepository.findByName(name);
    }

    @Override
    public TriggerValue save(TriggerValue triggerValue) {
        return this.triggerValueRepository.save(triggerValue);
    }
}
