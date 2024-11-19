package com.coursework.modforge.mapper;

import com.coursework.modforge.dto.GameCreationDto;
import com.coursework.modforge.dto.GameDto;
import com.coursework.modforge.entity.Game;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {GameCategoryMapper.class})
public interface GameMapper {
    Game toEntity(GameDto gameDto);

    GameDto toDto(Game game);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Game partialUpdate(GameDto gameDto, @MappingTarget Game game);

    Game toEntity(GameCreationDto gameCreationDto);

}