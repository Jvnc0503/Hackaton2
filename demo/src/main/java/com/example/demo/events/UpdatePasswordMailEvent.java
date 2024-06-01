package com.example.demo.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordMailEvent {
    private String name;
    private String email;

    public UpdatePasswordMailEvent(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
