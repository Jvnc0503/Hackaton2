package com.example.demo.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterMailEvent {
    private String name;
    private String email;

    public RegisterMailEvent(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
