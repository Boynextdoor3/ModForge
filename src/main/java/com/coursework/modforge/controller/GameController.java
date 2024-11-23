package com.coursework.modforge.controller;


import com.coursework.modforge.dto.GameCreationDto;
import com.coursework.modforge.dto.GameDto;
import com.coursework.modforge.exception.GameAlreadyExistException;
import com.coursework.modforge.exception.GameNotFoundException;
import com.coursework.modforge.service.GameService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/games")
@AllArgsConstructor
public class GameController {
    private final GameService gameService;

    @GetMapping("{id}")
    public ResponseEntity<GameDto> getGameById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(gameService.getById(id));
        } catch (GameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GameDto> createGame(@Valid @RequestBody GameCreationDto gameCreationDto) {
        try {
            return new ResponseEntity(gameService.create(gameCreationDto), HttpStatus.CREATED);
        } catch (GameAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GameDto> updateGame(@PathVariable Long id, @RequestBody GameDto gameDto) {
        try {
            return new ResponseEntity(gameService.update(id, gameDto), HttpStatus.OK);
        } catch (GameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (GameAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        try {
            gameService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (GameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
