package com.coursework.modforge.service;


import com.coursework.modforge.dto.GameCategoryCreationDto;
import com.coursework.modforge.dto.GameCategoryDto;
import com.coursework.modforge.entity.GameCategory;
import com.coursework.modforge.exception.GameCategoryAlreadyExistException;
import com.coursework.modforge.exception.GameCategoryNotFoundException;
import com.coursework.modforge.exception.ModTypeAlreadyExistsException;
import com.coursework.modforge.exception.ModTypeNotFoundException;
import com.coursework.modforge.mapper.GameCategoryMapper;
import com.coursework.modforge.repository.GameCategoryRepository;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class GameCategoryService {
    private final GameCategoryRepository gameCategoryRepository;
    private  final GameCategoryMapper gameCategoryMapper;

    public GameCategoryDto getById(Long id){
        GameCategory gameCategory = gameCategoryRepository.findById(id)
                .orElseThrow(() -> new GameCategoryNotFoundException("Category not found"));
        return gameCategoryMapper.toDto(gameCategory);
    }

    public List<GameCategoryDto> getAll(){
        try {
            List<GameCategory> gameCategories = gameCategoryRepository.findAll();
            return gameCategories.stream()
                    .map(gameCategoryMapper::toDto)
                    .toList();
        }catch (Exception ex) {
            throw new ServiceException("An unexpected error occurred while retrieving all game categories", ex);
        }
    }

    @Transactional
    public GameCategoryDto create(GameCategoryCreationDto gameCategoryCreationDto){
        if (gameCategoryRepository.existsByName(gameCategoryCreationDto.name())) {
            throw new GameCategoryAlreadyExistException("Game category with name '" + gameCategoryCreationDto.name() + "' already exists.");
        }
        return gameCategoryMapper.toDto(gameCategoryRepository.save(gameCategoryMapper.toEntity(gameCategoryCreationDto)));
    }

    @Transactional
    public GameCategoryDto update(Long id, GameCategoryDto gameCategoryDto){
        GameCategory gameCategory = gameCategoryRepository.findById(id)
                .orElseThrow(() -> new GameCategoryNotFoundException("Category not found"));
        if (gameCategoryRepository.existsByName(gameCategoryDto.name())) {
            throw new GameCategoryAlreadyExistException("Game category with name '" + gameCategoryDto.name() + "' already exists.");
        }
        gameCategoryMapper.partialUpdate(gameCategoryDto, gameCategory);
        gameCategory.setName(gameCategoryDto.name());
        return gameCategoryMapper.toDto(gameCategoryRepository.save(gameCategory));
    }

    @Transactional
    public void delete(Long id){
        if (!gameCategoryRepository.existsById(id)) {
            throw new GameCategoryNotFoundException("GameCategory with ID " + id + " not found");
        }
        gameCategoryRepository.deleteById(id);
    }

}
