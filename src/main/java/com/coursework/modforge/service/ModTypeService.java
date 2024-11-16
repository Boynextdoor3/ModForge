package com.coursework.modforge.service;

import com.coursework.modforge.dto.ModTypeCreationDto;
import com.coursework.modforge.dto.ModTypeDto;
import com.coursework.modforge.entity.ModType;
import com.coursework.modforge.exception.ModTypeNotFoundException;
import com.coursework.modforge.mapper.ModTypeMapper;
import com.coursework.modforge.repository.ModTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class ModTypeService {
    private final ModTypeRepository modTypeRepository;
    private final ModTypeMapper modTypeMapper;

    public ModTypeDto getById(Long id){
        ModType modType = modTypeRepository.findById(id).orElseThrow(() -> new ModTypeNotFoundException("Mod type not found"));
        return modTypeMapper.toDto(modType);
    }

    public List<ModTypeDto> getAll(){
        List<ModType> modTypes = modTypeRepository.findAll();
        return  modTypes.stream()
                .map(modTypeMapper::toDto)
                .toList();
    }

    @Transactional
    public ModTypeDto create(ModTypeCreationDto modTypeCreationDto){
        return modTypeMapper.toDto(modTypeRepository.save(modTypeMapper.toEntity(modTypeCreationDto)));
    }
}
