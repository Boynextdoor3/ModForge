package com.coursework.modforge.controller;


import com.coursework.modforge.dto.GameCreationDto;
import com.coursework.modforge.dto.GameDto;
import com.coursework.modforge.service.GameService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@AllArgsConstructor
public class GameController {
    private final GameService gameService;

    @GetMapping("{id}")
    public ResponseEntity<GameDto> getGameById(@PathVariable Long id){
        return ResponseEntity.ok(gameService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<GameDto>> getAllGames(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        if (!sortBy.equals("title")) {
            throw new IllegalArgumentException("Invalid sortBy field: " + sortBy);
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<GameDto> games = gameService.getAll(categoryId, pageable);
        return ResponseEntity.ok(games);
    }

    @PostMapping
    public ResponseEntity<GameDto> createGame(@Valid @RequestBody GameCreationDto gameCreationDto){
        return new ResponseEntity(gameService.create(gameCreationDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<GameDto> updateGame(@PathVariable Long id, @RequestBody GameDto gameDto){
        return new ResponseEntity(gameService.update(id, gameDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id){
        gameService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
