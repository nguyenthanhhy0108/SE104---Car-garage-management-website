package com.example.se.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {
    @GetMapping("/test")
     public String testPage(){
        return "test";
    }

    @PostMapping("/test")
    public ResponseEntity<String> handleForm(@RequestParam("formId") String formId) {
        System.out.println(formId);
        // Kiểm tra id của form
        if ("form1".equals(formId)) {
            System.out.println("Form 1 handled successfully");
            return new ResponseEntity<>("Form 1 handled successfully", HttpStatus.OK);
        } else if ("form2".equals(formId)) {
            // Xử lý form 2
            System.out.println("Form 2 handled successfully");
            return new ResponseEntity<>("Form 2 handled successfully", HttpStatus.OK);
        } else {
            // Id không hợp lệ
            System.out.println("Form 3 handled successfully");
            return new ResponseEntity<>("Invalid form id", HttpStatus.BAD_REQUEST);
        }
    }
}
