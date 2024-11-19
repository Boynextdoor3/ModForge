package com.coursework.modforge.service;


import com.coursework.modforge.dto.GameCategoryCreationDto;
import com.coursework.modforge.dto.GameCategoryDto;
import com.coursework.modforge.entity.GameCategory;
import com.coursework.modforge.exception.GameCategoryNotFoundException;
import com.coursework.modforge.mapper.GameCategoryMapper;
import com.coursework.modforge.repository.GameCategoryRepository;
import lombok.AllArgsConstructor;
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
        GameCategory gameCategory = gameCategoryRepository.findById(id).orElseThrow(() -> new GameCategoryNotFoundException("Category not found"));
        return gameCategoryMapper.toDto(gameCategory);
    }

    public List<GameCategoryDto> getAll(){
        List<GameCategory> gameCategories = gameCategoryRepository.findAll();
        return gameCategories.stream()
                .map(gameCategoryMapper::toDto)
                .toList();
    }

    @Transactional
    public GameCategoryDto create(GameCategoryCreationDto gameCategoryCreationDto){
        return gameCategoryMapper.toDto(gameCategoryRepository.save(gameCategoryMapper.toEntity(gameCategoryCreationDto)));
    }

    @Transactional
    public GameCategoryDto update(Long id, GameCategoryDto gameCategoryDto){
        GameCategory gameCategory = gameCategoryRepository.findById(id).orElseThrow(() -> new GameCategoryNotFoundException("Category not found"));
        gameCategoryMapper.partialUpdate(gameCategoryDto, gameCategory);
        gameCategory.setName(gameCategoryDto.name());
        return gameCategoryMapper.toDto(gameCategoryRepository.save(gameCategory));
    }

    @Transactional
    public void delete(Long id){
        gameCategoryRepository.deleteById(id);
    }

}
