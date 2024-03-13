package com.example.se.model;

import lombok.Data;

import java.time.LocalDateTime;

//Class for defining and initializing email structure
@Data
public class verification_email_structure {
    private String subject;
    private String message;
    private String verification_code;
    private LocalDateTime sent_time;

    public verification_email_structure() {
        //Initialize mail subject
        this.subject = "SE verification code";
        //Initialize mail content
        this.message = "<html><body>"
                    + "<div style=\"max-width: 400px; margin: 0 auto; padding: 20px; border: 1px solid #ccc; border-radius: 5px; background-color: #f9f9f9;\">"
                    + "<img src='cid:logo' alt=\"Alternative text\" style=\"max-width: 200px; max-height: 200px;\">"
                    + "<hr><h1 style=\"text-align: left; margin-bottom: 20px;\">Verification Code</h1><br>"
                    + "<p>Hello.</p>"
                    + "<p>To reset your password, please verify that is you by entering the code.</p>"
                    + "<p>Verification code expires in 30 minutes</p><br>"
                    + "<p style=\"font-size: 18px; text-align: center;\">Your verification code is:</p>"
                    + "<p style=\"font-size: 36px; text-align: center; font-weight: bold; color: #007bff;\">******</p><br>"
                    + "<p>Code expired? Try it again to receive a new code.</p>"
                    + "<p>If you did not request this code, please ignore this. SE automatic mail cannot be created.</p>"
                    + "<p>If you experience any issues regarding email verification or account creation, please contact our: <a href=\"http://facebook.com\">SEsupport.com</a></p><hr>"
                    + "<p style=\"font-family: Arial, sans-serif; font-size: 10px; color: #999;\">"
                    + "This message was sent from an unmonitored email address. Please do not reply to this message.</p>"
                    + "<p style=\"font-family: Arial, sans-serif; font-size: 10px; color: #999;\">"
                    + "© 2024 <span style=\"font-weight: bold;\">SE Application</span>, Inc. All rights reserved.</p></div>"
                    + "</body></html>";
        //Initialize null
        this.verification_code = null;
        this.sent_time = null;
    }

    //Function is used for put verification code into mail content
    public void replace_code(){
        this.message = this.message.replace("******", this.verification_code);
    }
}
