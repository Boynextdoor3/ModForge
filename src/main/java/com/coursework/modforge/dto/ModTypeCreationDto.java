package com.coursework.modforge.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.coursework.modforge.entity.ModType}
 */
public record ModTypeCreationDto(@NotNull @Size(max = 255) @NotEmpty @NotBlank String name) implements Serializable {
}
