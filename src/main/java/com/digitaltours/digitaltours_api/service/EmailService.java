package com.digitaltours.digitaltours_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendConfirmationEmail(String to, String username) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
        helper.setFrom("DigitalTours2024@gmail.com", "Digital Tours");

        helper.setTo(to);
        helper.setSubject("Confirmación de Registro en Digital Tours");
        helper.setText("Hola " + username + ",\n\n"
                + "Gracias por registrarte en Digital Tours. \n\n"
                + "Usuario: " + username + "\n"
                + "Email: " + to + "\n\n"
                + "Por favor, haz clic en el siguiente enlace para ingresar a tu cuenta: \n"
                + "http://localhost:5173/login", false);
        mailSender.send(message);
    }
}

