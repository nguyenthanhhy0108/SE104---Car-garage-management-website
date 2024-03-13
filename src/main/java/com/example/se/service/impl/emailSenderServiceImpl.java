package com.example.se.service.impl;

import com.example.se.model.verification_email_structure;
import com.example.se.service.emailSenderService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

//Implement method in Service layer
@Service
public class emailSenderServiceImpl implements emailSenderService {

    //Define and initialize internal attribute
    private final JavaMailSender sender;

    @Autowired
    public emailSenderServiceImpl(JavaMailSender sender) {
        this.sender = sender;
    }

    /*
    * Input: Destination email, email structure
    *   Create mail content and add some necessary fields
    *   Add embedded image into mail content
    *   Send
    *   Try and catch exception if it existed
    * */
    @Override
    public void sendEmail(String toEmail, verification_email_structure verificationEmailStructure) {
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

    //Function for create a random verification sequence(6 units) by number and upper/lower letter
    @Override
    public String randomVerificationCode() {
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
}
