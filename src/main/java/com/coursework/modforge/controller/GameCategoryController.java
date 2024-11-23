package com.coursework.modforge.controller;

import com.coursework.modforge.dto.GameCategoryCreationDto;
import com.coursework.modforge.dto.GameCategoryDto;
import com.coursework.modforge.exception.GameAlreadyExistException;
import com.coursework.modforge.exception.GameCategoryAlreadyExistException;
import com.coursework.modforge.exception.GameCategoryNotFoundException;
import com.coursework.modforge.service.GameCategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games/category")
@AllArgsConstructor
public class GameCategoryController {
    private final GameCategoryService gameCategoryService;

    @GetMapping("{id}")
    public ResponseEntity<GameCategoryDto> getGameCategoryById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(gameCategoryService.getById(id));
        }catch (GameCategoryNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<GameCategoryDto>> getAllGameCategory(){
        try {
            return  ResponseEntity.ok(gameCategoryService.getAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GameCategoryDto> createGameCategory(@Valid @RequestBody GameCategoryCreationDto gameCategoryCreationDto){
        try {
            return new ResponseEntity(gameCategoryService.create(gameCategoryCreationDto), HttpStatus.CREATED);
        }catch (GameCategoryAlreadyExistException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GameCategoryDto> updateGameCategory(@PathVariable Long id, @RequestBody GameCategoryDto gameCategoryDto){
        try {
            return new ResponseEntity(gameCategoryService.update(id, gameCategoryDto), HttpStatus.OK);
        }catch (GameCategoryNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch (GameCategoryAlreadyExistException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteGameCategory(@PathVariable Long id){
        try {
            gameCategoryService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (GameCategoryNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
