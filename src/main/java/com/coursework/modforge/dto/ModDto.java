package com.coursework.modforge.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.coursework.modforge.entity.Mod}
 */
public record ModDto(Long id,
                     String title,
                     String description,
                     GameDto game,
                     ModTypeDto type,
                     UserDto modCreator,
                     LocalDate addedDate) implements Serializable {
  }