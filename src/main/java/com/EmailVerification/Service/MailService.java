package com.EmailVerification.Service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String email, String token) throws Exception{

        String link = "http://localhost:8080/verify?token=" + token;
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject("Email Verification");
        helper.setText("<h3>Click the link to verify:</h3><a href='"+link+"'>Verify</a>", true);

        mailSender.send(message);
    }
}
