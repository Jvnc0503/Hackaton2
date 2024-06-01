package com.example.demo.events;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component

public class EmailListener {

    private final EmailService emailService;

    @Autowired
    public EmailListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @EventListener
    @Async
    public void handleRegisterEmailEvent(RegisterMailEvent event) {
        emailService.sendRegisterMessage(event.getEmail(), "Registro Exitoso",
                event.getName());
    }

    @EventListener
    @Async
    public void handleUpdatePasswordMailEvent(UpdatePasswordMailEvent event) {
        emailService.sendUpdatePasswordMessage(event.getEmail(),"Registro Exitoso", event.getName());
    }

}