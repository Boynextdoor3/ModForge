package com.coursework.modforge.service;

import com.coursework.modforge.dto.ModCreationDto;
import com.coursework.modforge.dto.ModDto;
import com.coursework.modforge.entity.Mod;
import com.coursework.modforge.exception.ModNotFoundException;
import com.coursework.modforge.mapper.ModMapper;
import com.coursework.modforge.repository.ModRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ModService {
    private final ModRepository modRepository;
    private final ModMapper modMapper;

    public ModDto getById(Long id){
        Mod mod = modRepository.findById(id).orElseThrow(() -> new ModNotFoundException("Mod not found"));
        return modMapper.toDto(mod);
    }

    public List<ModDto> getAll(){
        List<Mod> mods = modRepository.findAll();
        return  mods.stream()
                .map(modMapper::toDto)
                .toList();
    }

    @Transactional
    public ModDto create(ModCreationDto modCreationDto){
        return modMapper.toDto(modRepository.save(modMapper.toEntity(modCreationDto)));
    }
}
