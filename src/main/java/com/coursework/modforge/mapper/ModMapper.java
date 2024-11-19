package com.coursework.modforge.mapper;

import com.coursework.modforge.dto.ModCreationDto;
import com.coursework.modforge.dto.ModDto;
import com.coursework.modforge.entity.Mod;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {GameMapper.class, ModTypeMapper.class, UserMapper.class})
public interface ModMapper {
    Mod toEntity(ModDto modDto);

    ModDto toDto(Mod mod);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Mod partialUpdate(ModDto modDto, @MappingTarget Mod mod);

    Mod toEntity(ModCreationDto modCreationDto);

}
