package com.example.demo.exceptions;

public class UniqueResourceAlreadyExists extends RuntimeException {
    public UniqueResourceAlreadyExists(String message) {
        super(message);
    }

}