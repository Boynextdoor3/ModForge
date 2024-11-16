package com.coursework.modforge.controller;


import com.coursework.modforge.dto.GameCreationDto;
import com.coursework.modforge.dto.GameDto;
import com.coursework.modforge.service.GameService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<List<GameDto>> getAllGames(){
        return  ResponseEntity.ok(gameService.getAll());
    }

    @PostMapping
    public ResponseEntity<GameDto> createGame(@Valid @RequestBody GameCreationDto gameCreationDto){
        return new ResponseEntity(gameService.create(gameCreationDto), HttpStatus.CREATED);
    }
}
