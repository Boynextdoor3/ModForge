package com.coursework.modforge.service;

import com.coursework.modforge.dto.ModCreationDto;
import com.coursework.modforge.dto.ModDto;
import com.coursework.modforge.entity.Mod;
import com.coursework.modforge.exception.ModNotFoundException;
import com.coursework.modforge.mapper.ModMapper;
import com.coursework.modforge.repository.ModRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
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

    public Page<ModDto> getAll(Long typeId, Long userId, Pageable pageable) {
        Specification<Mod> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (typeId != null) {
                predicates.add(criteriaBuilder.equal(root.join("type").get("id"), typeId));
            }
            if (userId != null) {
                predicates.add(criteriaBuilder.equal(root.join("modCreator").get("id"), userId));
            }

            return predicates.isEmpty() ? null : criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Page<Mod> mods = modRepository.findAll(specification, pageable);
        return mods.map(modMapper::toDto);
    }

    @Transactional
    public ModDto create(ModCreationDto modCreationDto){
        return modMapper.toDto(modRepository.save(modMapper.toEntity(modCreationDto)));
    }

    @Transactional
    public ModDto update(Long id,ModDto modDto){
        Mod mod = modRepository.findById(id).orElseThrow(() -> new ModNotFoundException("Mod not found"));
        modMapper.partialUpdate(modDto, mod);
        mod.setTitle(modDto.title());
        mod.setDescription(modDto.description());
        return modMapper.toDto(modRepository.save(mod));
    }

    @Transactional
    public void delete(Long id){
        modRepository.deleteById(id);
    }
}
