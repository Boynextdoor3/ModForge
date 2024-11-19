package com.coursework.modforge.controller;

import com.coursework.modforge.dto.ModTypeCreationDto;
import com.coursework.modforge.dto.ModTypeDto;
import com.coursework.modforge.service.ModTypeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mods/type")
@AllArgsConstructor
public class ModTypeController {
    private final ModTypeService modTypeService;

    @GetMapping("{id}")
    public ResponseEntity<ModTypeDto> getModTypeById(@PathVariable Long id){
        return ResponseEntity.ok(modTypeService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ModTypeDto>> getAllModTypes(){
        return ResponseEntity.ok(modTypeService.getAll());
    }

    @PostMapping
    public ResponseEntity<ModTypeDto> createModType(@Valid @RequestBody ModTypeCreationDto modTypeCreationDto){
        return new ResponseEntity(modTypeService.create(modTypeCreationDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ModTypeDto> updateModType(@PathVariable Long id, @RequestBody ModTypeDto modTypeDto){
        return new ResponseEntity(modTypeService.update(id, modTypeDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteModType(@PathVariable Long id){
        modTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
