package infrastructure.controller;

import application.dto.RegisterUserRequest;
import application.dto.UserResponse;
import application.usecase.RegisterUserUseCase;
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