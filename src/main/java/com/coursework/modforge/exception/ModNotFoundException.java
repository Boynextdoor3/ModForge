package com.coursework.modforge.exception;

import jakarta.persistence.EntityNotFoundException;

public class ModNotFoundException extends EntityNotFoundException {
    public ModNotFoundException(String message){
        super(message);
    }
}
