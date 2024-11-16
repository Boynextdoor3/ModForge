package com.coursework.modforge.controller;

import com.coursework.modforge.dto.GameCategoryCreationDto;
import com.coursework.modforge.dto.GameCategoryDto;
import com.coursework.modforge.service.GameCategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games/category")
@AllArgsConstructor
public class GameCategoryController {
    private final GameCategoryService gameCategoryService;

    @GetMapping("{id}")
    public ResponseEntity<GameCategoryDto> getGameCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(gameCategoryService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<GameCategoryDto>> getAllGameCategory(){
        return  ResponseEntity.ok(gameCategoryService.getAll());
    }

    @PostMapping
    public ResponseEntity<GameCategoryDto> createGameCategory(@Valid @RequestBody GameCategoryCreationDto gameCategoryCreationDto){
        return new ResponseEntity(gameCategoryService.create(gameCategoryCreationDto), HttpStatus.CREATED);
    }

}
