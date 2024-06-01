package com.example.demo.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PlaylistCreatedEvent extends ApplicationEvent {
    private final String email;

    public PlaylistCreatedEvent(String email) {
        super(email);
        this.email = email;
    }
}
