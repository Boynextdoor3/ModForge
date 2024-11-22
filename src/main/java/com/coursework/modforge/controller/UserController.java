package com.coursework.modforge.controller;


import com.coursework.modforge.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
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
