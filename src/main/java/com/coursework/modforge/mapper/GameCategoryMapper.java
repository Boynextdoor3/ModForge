package com.coursework.modforge.mapper;

import com.coursework.modforge.dto.GameCategoryCreationDto;
import com.coursework.modforge.dto.GameCategoryDto;
import com.coursework.modforge.entity.GameCategory;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GameCategoryMapper {
    GameCategory toEntity(GameCategoryDto gameCategoryDto);

    GameCategoryDto toDto(GameCategory gameCategory);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    GameCategory partialUpdate(GameCategoryDto gameCategoryDto, @MappingTarget GameCategory gameCategory);

    GameCategory toEntity(GameCategoryCreationDto gameCategoryCreationDto);
}