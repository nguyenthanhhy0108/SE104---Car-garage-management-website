package com.example.se.service;

import com.example.se.model.verificationEmailStructure;

//Define methods for Service layer which is got by Controller
public interface emailService {
    /**
     * Send email service to destination email
     * @param toEmail: Destination email
     * @param verificationEmailStructure: Fully context
     */
    void sendEmail(String toEmail, verificationEmailStructure verificationEmailStructure);

    /**
     * Random verification code which will be embedded in the context
     * @return
     * Code (String)
     */
    String randomVerificationCode();

    /**
     * Prepare email structure
     * @param verificationEmailStructure: verificationEmailStructure object
     */
    void prepareMail(verificationEmailStructure verificationEmailStructure);

    /**
     * Check code in the context with received code from client
     * @param verificationCode: String
     * @param verificationEmailStructure: verificationEmailStructure object
     * @return
     * A boolean
     */
    boolean checkVerificationCodeMatch(String verificationCode, verificationEmailStructure verificationEmailStructure);

    /**
     * Check code in the context expire when client submit form
     * @param verificationEmailStructure: verificationEmailStructure object
     * @return
     * A boolean
     */
    boolean checkVerificationCodeExpired(verificationEmailStructure verificationEmailStructure);
}
