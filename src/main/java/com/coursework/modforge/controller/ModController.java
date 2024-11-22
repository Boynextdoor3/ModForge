package com.coursework.modforge.controller;

import com.coursework.modforge.dto.ModCreationDto;
import com.coursework.modforge.dto.ModDto;
import com.coursework.modforge.exception.ModAlreadyExistException;
import com.coursework.modforge.exception.ModNotFoundException;
import com.coursework.modforge.service.ModService;
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
@RequestMapping("/api/mods")
@AllArgsConstructor
public class ModController {
    private final ModService modService;

    @GetMapping("{id}")
    public ResponseEntity<ModDto> getModById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(modService.getById(id));
        } catch (ModNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<Page<ModDto>> getAllMods(
            @RequestParam(required = false) Long typeId,
            @RequestParam(required = false) Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "addedDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {

        if (!List.of("title", "addedDate", "description").contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sortBy field: " + sortBy);
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<ModDto> mods = modService.getAll(typeId, userId, pageable);
        return ResponseEntity.ok(mods);
    }

    @PostMapping
    public ResponseEntity<ModDto> createMod(@Valid @RequestBody ModCreationDto modCreationDto) {
        try {
            return new ResponseEntity(modService.create(modCreationDto), HttpStatus.CREATED);
        } catch (ModAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ModDto> updateMod(@PathVariable Long id, @RequestBody ModDto modDto) {
        try {
            return new ResponseEntity(modService.update(id, modDto), HttpStatus.OK);
        } catch (ModNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (ModAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMod(@PathVariable Long id) {
        try {
            modService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ModNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
