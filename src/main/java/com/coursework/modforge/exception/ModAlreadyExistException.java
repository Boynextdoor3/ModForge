package com.coursework.modforge.exception;

public class ModAlreadyExistException extends RuntimeException {
    public ModAlreadyExistException(String message) {
        super(message);
    }
}
