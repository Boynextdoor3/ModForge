package com.coursework.modforge.exception;

public class ModTypeAlreadyExistsException extends RuntimeException {
    public ModTypeAlreadyExistsException(String message) {
        super(message);
    }
}
