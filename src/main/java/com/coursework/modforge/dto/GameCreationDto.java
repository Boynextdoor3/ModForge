package com.coursework.modforge.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.coursework.modforge.entity.Game}
 */
public record GameCreationDto(@NotNull @Size(max = 255) @NotEmpty @NotBlank String title,
                              @NotNull GameCategoryDto category) implements Serializable {
}
