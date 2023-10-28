package com.example.demo.exceptions;

public class LikeAlreadyExistException extends RuntimeException {
    public LikeAlreadyExistException(String message) {
        super(message);
    }
}
