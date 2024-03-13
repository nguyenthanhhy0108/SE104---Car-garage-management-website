package com.example.se.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {
    //Config email sender, protocol
    @Bean
    public JavaMailSender getJavaMailSender() {
        //Protocol: SMTP
        //Port: 587
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        //Define gmail and SE application password for mail sender
        mailSender.setUsername("noreply.seapplication@gmail.com");
        mailSender.setPassword("konq ibbe cbvb apto");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
