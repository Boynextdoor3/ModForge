package com.coursework.modforge.service;

import com.coursework.modforge.dto.ModTypeCreationDto;
import com.coursework.modforge.dto.ModTypeDto;
import com.coursework.modforge.entity.ModType;
import com.coursework.modforge.exception.ModTypeAlreadyExistsException;
import com.coursework.modforge.exception.ModTypeNotFoundException;
import com.coursework.modforge.mapper.ModTypeMapper;
import com.coursework.modforge.repository.ModTypeRepository;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class ModTypeService {

    private final ModTypeRepository modTypeRepository;
    private final ModTypeMapper modTypeMapper;


    public ModTypeDto getById(Long id) {
        ModType modType = modTypeRepository.findById(id)
                .orElseThrow(() -> new ModTypeNotFoundException("Mod type with ID " + id + " not found"));
        return modTypeMapper.toDto(modType);
    }

    public List<ModTypeDto> getAll() {
        try {
            List<ModType> modTypes = modTypeRepository.findAll();
            return modTypes.stream()
                    .map(modTypeMapper::toDto)
                    .toList();
        } catch (Exception ex) {
            throw new ServiceException("An unexpected error occurred while retrieving all mod types", ex);
        }
    }

    @Transactional
    public ModTypeDto create(ModTypeCreationDto modTypeCreationDto) {
        if (modTypeRepository.existsByName(modTypeCreationDto.name())) {
            throw new ModTypeAlreadyExistsException("ModType with name '" + modTypeCreationDto.name() + "' already exists.");
        }
        return modTypeMapper.toDto(modTypeRepository.save(modTypeMapper.toEntity(modTypeCreationDto)));
    }

    @Transactional
    public ModTypeDto update(Long id, ModTypeDto modTypeDto) {
        ModType modType = modTypeRepository.findById(id).orElseThrow(() -> new ModTypeNotFoundException("Mod type with ID " + id + " not found"));
        if (modTypeRepository.existsByName(modTypeDto.name())) {
            throw new ModTypeAlreadyExistsException("ModType with name '" + modTypeDto.name() + "' already exists.");
        }
        modTypeMapper.partialUpdate(modTypeDto, modType);
        modType.setName(modTypeDto.name());
        return modTypeMapper.toDto(modTypeRepository.save(modType));
    }

    @Transactional
    public void delete(Long id) {
        if (!modTypeRepository.existsById(id)) {
            throw new ModTypeNotFoundException("Mod type with ID " + id + " not found");
        }
        modTypeRepository.deleteById(id);
    }
}
