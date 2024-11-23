package com.coursework.modforge.controller;

import com.coursework.modforge.dto.ModTypeCreationDto;
import com.coursework.modforge.dto.ModTypeDto;
import com.coursework.modforge.exception.ModTypeAlreadyExistsException;
import com.coursework.modforge.exception.ModTypeNotFoundException;
import com.coursework.modforge.service.ModTypeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mods/type")
@AllArgsConstructor
public class ModTypeController {
    private final ModTypeService modTypeService;

    @GetMapping("{id}")
    public ResponseEntity<ModTypeDto> getModTypeById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(modTypeService.getById(id));
        } catch (ModTypeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<ModTypeDto>> getAllModTypes() {
        try {
            return ResponseEntity.ok(modTypeService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ModTypeDto> createModType(@Valid @RequestBody ModTypeCreationDto modTypeCreationDto) {
        try {
            return new ResponseEntity<>(modTypeService.create(modTypeCreationDto), HttpStatus.CREATED);
        } catch (ModTypeAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PutMapping("{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ModTypeDto> updateModType(@PathVariable Long id, @RequestBody ModTypeDto modTypeDto) {
        try {
            return new ResponseEntity<>(modTypeService.update(id, modTypeDto), HttpStatus.OK);
        } catch (ModTypeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch (ModTypeAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @DeleteMapping("{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteModType(@PathVariable Long id) {
        try {
            modTypeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ModTypeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
