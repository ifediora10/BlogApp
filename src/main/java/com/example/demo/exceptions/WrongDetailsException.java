package com.example.demo.exceptions;

public class WrongDetailsException extends RuntimeException {
    public WrongDetailsException(String message) {
        super(message);
    }
}
