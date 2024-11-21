package com.coursework.modforge.dto;

import jakarta.validation.constraints.*;

import com.coursework.modforge.entity.Role;


import java.io.Serializable;

/**
 * DTO for {@link com.coursework.modforge.entity.User}
 */
public record UserCreationDto( @NotNull @Size(max = 255) @NotEmpty @NotBlank String username,
                               @NotNull @Email( regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$") @NotEmpty @NotBlank String email,
                               @NotNull @Size(min = 6, max = 20) @NotEmpty @NotBlank String password,
                               @NotNull Role role) implements Serializable {

}