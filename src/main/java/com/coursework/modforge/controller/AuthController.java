package com.coursework.modforge.controller;

import com.coursework.modforge.dto.JwtAuthenticationResponse;
import com.coursework.modforge.dto.SignInRequest;
import com.coursework.modforge.dto.SignUpRequest;
import com.coursework.modforge.service.AuthenticationService;
import com.coursework.modforge.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }

    @GetMapping("/get-admin")
    public void getAdmin() {
        userService.getAdmin();
    }
}
