package com.example.se.service.impl;

import com.example.se.model.verificationEmailStructure;
import com.example.se.service.emailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

//Implement method in Service layer
@Service
public class emailServiceImpl implements emailService {

    //Define and initialize internal attribute
    private final JavaMailSender sender;

    /**
     * Dependency Injection
     * @param sender
     */
    @Autowired
    public emailServiceImpl(JavaMailSender sender) {
        this.sender = sender;
    }

    /**
     * Sending mail
     * @param toEmail: Destination email
     * @param verificationEmailStructure: Fully context
     */
    @Override
    public void sendEmail(String toEmail,
                          verificationEmailStructure verificationEmailStructure) {
        /*
         * Input: Destination email, email structure
         *   Create mail content and add some necessary fields
         *   Add embedded image into mail content
         *   Send
         *   Try and catch exception if it existed
         * */
        MimeMessage message = sender.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject(verificationEmailStructure.getSubject());
            helper.setSentDate(new Date());

            helper.setText(verificationEmailStructure.getMessage(), true);
            helper.addInline("logo", new ClassPathResource("/static/images/logo.png"));

            sender.send(message);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    /**
     * Create a random code
     * @return
     * Code (String)
     */
    @Override
    public String randomVerificationCode() {
        //Function for create a random verification sequence(6 units) by number and upper/lower letter
        int length = 4;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();

        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            code.append(randomChar);
        }
        return code.toString();
    }

    /**
     * Prepare mail
     * @param verificationEmailStructure: verificationEmailStructure object
     */
    @Override
    public void prepareMail(verificationEmailStructure verificationEmailStructure) {
        verificationEmailStructure.setVerification_code(this.randomVerificationCode());
        verificationEmailStructure.replace_code();
        verificationEmailStructure.setSent_time(LocalDateTime.now());
    }

    /**
     * Check code in the context with provided code from client
     * @param verificationCode: String
     * @param verificationEmailStructure: verificationEmailStructure object
     * @return
     * A boolean
     */
    @Override
    public boolean checkVerificationCodeMatch(String verificationCode,
                                              verificationEmailStructure verificationEmailStructure) {
        if(verificationEmailStructure.getVerification_code().equals(verificationCode)){
            return true;
        }
        return false;
    }

    /**
     * Check expired code when client submit form
     * @param verificationEmailStructure: verificationEmailStructure object
     * @return
     * A boolean
     */
    @Override
    public boolean checkVerificationCodeExpired(verificationEmailStructure verificationEmailStructure) {
        LocalDateTime now = LocalDateTime.now();
        if(now.isAfter(verificationEmailStructure.getSent_time().plusMinutes(30))){
            return true;
        }
        return false;
    }
}
