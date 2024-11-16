package com.coursework.modforge.dto;

import java.io.Serializable;
import com.coursework.modforge.entity.GameCategory;

/**
 * DTO for {@link com.coursework.modforge.entity.Game}
 */
public record GameDto(Long id, String title, GameCategory category) implements Serializable {

}
