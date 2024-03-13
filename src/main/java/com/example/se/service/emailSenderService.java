package com.example.se.service;

import com.example.se.model.verification_email_structure;

//Define methods for Service layer which is got by Controller
public interface emailSenderService {
    void sendEmail(String toEmail, verification_email_structure verificationEmailStructure);
    String randomVerificationCode();
}
