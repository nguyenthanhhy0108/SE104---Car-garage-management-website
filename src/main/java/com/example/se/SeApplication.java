package com.example.se;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example"})
public class SeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeApplication.class, args);
    }

}
