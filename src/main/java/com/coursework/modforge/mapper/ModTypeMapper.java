package com.coursework.modforge.mapper;

import com.coursework.modforge.dto.ModTypeCreationDto;
import com.coursework.modforge.dto.ModTypeDto;
import com.coursework.modforge.entity.ModType;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ModTypeMapper {
    ModType toEntity(ModTypeDto modTypeDto);

    ModTypeDto toDto(ModType modType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ModType partialUpdate(ModTypeDto modTypeDto, @MappingTarget ModType modType);

    ModType toEntity(ModTypeCreationDto modTypeCreationDto);
}
