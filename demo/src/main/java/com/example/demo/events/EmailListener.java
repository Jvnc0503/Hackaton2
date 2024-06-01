package com.example.demo.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {

    @Autowired
    private EmailService emailService;

    @EventListener
    @Async
    public void handleEvents(PlaylistCreatedEvent event) {
        emailService.sendSimpleMessage(event.getEmail(), "Playlist Creada", "Tu playlist ha sido creada!");
    }
}