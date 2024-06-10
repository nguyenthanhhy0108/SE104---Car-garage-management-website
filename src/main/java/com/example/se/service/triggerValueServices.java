package com.example.se.service;

import com.example.se.model.TriggerValue;

public interface triggerValueServices {
    TriggerValue findByName(String name);

    TriggerValue save(TriggerValue triggerValue);
}
