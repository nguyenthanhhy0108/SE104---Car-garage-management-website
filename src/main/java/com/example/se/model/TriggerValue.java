package com.example.se.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "TRIGGER_VALUE")
@Data
public class TriggerValue {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Value")
    private int value;

    public TriggerValue(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public TriggerValue() {
    }
}
