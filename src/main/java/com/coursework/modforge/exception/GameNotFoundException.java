package com.coursework.modforge.exception;

import jakarta.persistence.EntityNotFoundException;

public class GameNotFoundException extends EntityNotFoundException {
    public GameNotFoundException(String message){
        super(message);
    }
}
