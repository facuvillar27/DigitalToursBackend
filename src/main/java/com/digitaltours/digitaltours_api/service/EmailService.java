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
                + "http://digital-tours-frontend.s3-website-us-east-1.amazonaws.com/login", false);
        mailSender.send(message);
    }

    public void sendConfirmationReserve(String to, String username, String tourName, String date, String start_time, String confirmationCode) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
        helper.setFrom("DigitalTours2024@gmail.com", "Digital Tours");

        helper.setTo(to);
        helper.setSubject("Confirmación de Reserva en Digital Tours");
        helper.setText("Hola " + username + ",\n\n"
                + "Gracias por reservar un tour en Digital Tours. \n\n"
                + "Usuario: " + username + "\n"
                + "Email: " + to + "\n"
                + "Tour: " + tourName + "\n"
                + "Fecha: " + date + "\n"
                + "Hora: " + start_time + "\n"
                + "Codigo de reserva: " + confirmationCode + "\n"
                + "Por consultas comunicarse con DigitalTours2024@gmail.com \n\n", false);
        mailSender.send(message);
    }
}

