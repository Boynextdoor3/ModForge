package com.coursework.modforge.dto;

import com.coursework.modforge.entity.Mod;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.coursework.modforge.entity.ModType}
 */
public record ModTypeDto(Long id, String name) implements Serializable {

}
