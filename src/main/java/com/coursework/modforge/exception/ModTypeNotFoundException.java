package com.coursework.modforge.exception;

import jakarta.persistence.EntityNotFoundException;

public class ModTypeNotFoundException extends EntityNotFoundException {

    public ModTypeNotFoundException(String message){
        super(message);
    }
}
