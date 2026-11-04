package com.serhii.iambackend.infrastructure.controller;

import com.serhii.iambackend.application.dto.RegisterUserRequest;
import com.serhii.iambackend.application.dto.UserResponse;
import com.serhii.iambackend.application.usecase.RegisterUserUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;

    public AuthController(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/register")
    public UserResponse register(
            @RequestBody RegisterUserRequest request) {

        return registerUserUseCase.register(request);
    }
}