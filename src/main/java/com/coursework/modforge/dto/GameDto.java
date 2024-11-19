package com.coursework.modforge.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.coursework.modforge.entity.Game}
 */
public record GameDto(Long id, String title, GameCategoryDto category) implements Serializable {
  }