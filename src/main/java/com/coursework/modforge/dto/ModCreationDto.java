package com.coursework.modforge.dto;

import com.coursework.modforge.entity.Game;
import com.coursework.modforge.entity.ModType;
import com.coursework.modforge.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.coursework.modforge.entity.Mod}
 */
public record ModCreationDto(@NotNull @Size(max = 255) @NotEmpty @NotBlank String title,
                             @NotNull @Size(max = 255) @NotEmpty @NotBlank String description,
                             @NotNull Game game,
                             @NotNull ModType type,
                             @NotNull User modCreator) implements Serializable {
}
