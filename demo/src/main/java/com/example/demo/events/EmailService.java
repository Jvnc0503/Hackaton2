package com.example.demo.events;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendRegisterMessage(String emailto, String subject, String name){
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy 'a las' HH:mm 'hrs'", new Locale("es", "PE"));
        String formattedDate = zonedDateTime.withZoneSameInstant(ZoneId.of("America/Lima")).format(formatter);
        Context context = new Context();


        context.setVariable("date", formattedDate);
        context.setVariable("nombre", name);

        String body = templateEngine.process("BienvenidaMail",  context);
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom("milton.cordova@utec.edu.pe");
            messageHelper.setTo(emailto);
            messageHelper.setSubject(subject);
            messageHelper.setText(body, true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendUpdatePasswordMessage(String emailto, String subject, String name){
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy 'a las' HH:mm 'hrs'", new Locale("es", "PE"));
        String formattedDate = zonedDateTime.withZoneSameInstant(ZoneId.of("America/Lima")).format(formatter);
        Context context = new Context();


        context.setVariable("date", formattedDate);
        context.setVariable("nombre", name);

        String body = templateEngine.process("BienvenidaMail",  context);
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom("milton.cordova@utec.edu.pe");
            messageHelper.setTo(emailto);
            messageHelper.setSubject(subject);
            messageHelper.setText(body, true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}