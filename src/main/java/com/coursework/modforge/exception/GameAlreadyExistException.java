package com.coursework.modforge.exception;

public class GameAlreadyExistException extends RuntimeException {
    public GameAlreadyExistException(String message) {
        super(message);
    }
}
