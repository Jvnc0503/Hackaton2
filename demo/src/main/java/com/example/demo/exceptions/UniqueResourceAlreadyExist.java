package com.example.demo.exceptions;

public class UniqueResourceAlreadyExist extends RuntimeException {
    public UniqueResourceAlreadyExist(String message) {
        super(message);
    }
}