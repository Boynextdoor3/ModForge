package com.coursework.modforge.dto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.coursework.modforge.entity.GameCategory}
 */
public record GameCategoryDto(Long id, String name) implements Serializable {
}
