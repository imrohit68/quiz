package com.example.quizinternship.Exceptions;

public class InvalidRequestException extends RuntimeException {
    private String message;

    public InvalidRequestException(String message) {
        super(String.format("%s",message));
        this.message = message;
    }
}
