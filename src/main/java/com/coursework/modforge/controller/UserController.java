package com.coursework.modforge.controller;


import com.coursework.modforge.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

//    @GetMapping("{id}")
//    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
//        return ResponseEntity.ok(userService.getById(id));
//    }

//    @GetMapping
//    public ResponseEntity<List<UserDto>> getAllUsers(){
//        return ResponseEntity.ok(userService.getAll());
//    }

//    @PostMapping
//    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserCreationDto userCreationDto){
//        return new ResponseEntity(userService.create(userCreationDto), HttpStatus.CREATED);
//    }




}
