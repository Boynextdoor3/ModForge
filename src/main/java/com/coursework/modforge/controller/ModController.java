package com.coursework.modforge.controller;

import com.coursework.modforge.dto.ModCreationDto;
import com.coursework.modforge.dto.ModDto;
import com.coursework.modforge.service.ModService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<List<ModDto>> getAllMods(){
        return ResponseEntity.ok(modService.getAll());
    }

    @PostMapping
    public ResponseEntity<ModDto> createMod(@Valid @RequestBody ModCreationDto modCreationDto){
        return new ResponseEntity(modService.create(modCreationDto), HttpStatus.CREATED);
    }
}
