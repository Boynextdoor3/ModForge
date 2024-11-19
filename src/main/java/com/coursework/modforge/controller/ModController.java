package com.coursework.modforge.controller;

import com.coursework.modforge.dto.ModCreationDto;
import com.coursework.modforge.dto.ModDto;
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
    public ResponseEntity<ModDto> getModById(@PathVariable Long id){
        return ResponseEntity.ok(modService.getById(id));
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
    public ResponseEntity<ModDto> createMod(@Valid @RequestBody ModCreationDto modCreationDto){
        return new ResponseEntity(modService.create(modCreationDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ModDto> updateMod(@PathVariable Long id, @RequestBody ModDto modDto){
        return new ResponseEntity(modService.update(id, modDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMod(@PathVariable Long id){
        modService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
