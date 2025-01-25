package com.example.activity.exception;

public class DuplicateParticipantException extends RuntimeException {
    public DuplicateParticipantException(String message) {
        super(message);
    }
}
