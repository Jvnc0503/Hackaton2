package com.example.demo;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.exceptions.UnauthorizeOperationException;
import com.example.demo.exceptions.UniqueResourceAlreadyExists;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UniqueResourceAlreadyExists.class)
    public String handleNotFound(UniqueResourceAlreadyExists ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleNotFound(ResourceNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UnauthorizeOperationException.class)
    public String handleUnauthorize(UnauthorizeOperationException ex) {return ex.getMessage();}
}
