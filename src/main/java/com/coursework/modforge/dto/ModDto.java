package com.coursework.modforge.dto;

import com.coursework.modforge.entity.Game;
import com.coursework.modforge.entity.ModType;
import com.coursework.modforge.entity.User;

import java.io.Serializable;

/**
 * DTO for {@link com.coursework.modforge.entity.Mod}
 */
public record ModDto(Long id, String title, String description, Game game, ModType type,
                     User modCreator) implements Serializable {
}
