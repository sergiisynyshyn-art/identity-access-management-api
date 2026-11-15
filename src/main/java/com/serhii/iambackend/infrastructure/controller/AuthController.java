package com.serhii.iambackend.infrastructure.controller;

import com.serhii.iambackend.application.dto.LoginRequest;
import com.serhii.iambackend.application.dto.LoginResponse;
import com.serhii.iambackend.application.dto.RegisterUserRequest;
import com.serhii.iambackend.application.dto.UserResponse;
import com.serhii.iambackend.application.usecase.LoginUserUseCase;
import com.serhii.iambackend.application.usecase.RegisterUserUseCase;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;

    public AuthController(RegisterUserUseCase registerUserUseCase, LoginUserUseCase loginUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.loginUserUseCase = loginUserUseCase;
    }

    @PostMapping("/register")
    public UserResponse register(
            @Valid  @RequestBody RegisterUserRequest request) {

        return registerUserUseCase.register(request);
    }
    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request) {

        return loginUserUseCase.login(request);
    }
}