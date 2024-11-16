package com.coursework.modforge.dto;

import com.coursework.modforge.entity.Mod;
import com.coursework.modforge.entity.Role;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.coursework.modforge.entity.User}
 */
public record UserDto(Long id, String username, String email, String password, Role role) implements Serializable {
}
