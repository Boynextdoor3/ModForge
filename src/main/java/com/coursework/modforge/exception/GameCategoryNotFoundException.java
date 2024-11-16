package com.coursework.modforge.exception;

import com.coursework.modforge.entity.GameCategory;
import jakarta.persistence.EntityNotFoundException;

public class GameCategoryNotFoundException extends EntityNotFoundException {

    public GameCategoryNotFoundException(String message){
        super(message);
    }

}
