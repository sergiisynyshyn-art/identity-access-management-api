package com.serhii.iambackend.application.usecase;

import com.serhii.iambackend.application.dto.LoginRequest;
import com.serhii.iambackend.application.dto.LoginResponse;
import com.serhii.iambackend.domain.model.User;
import com.serhii.iambackend.domain.ports.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class LoginUserUseCaseImpl implements LoginUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public LoginUserUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepositoryPort.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid credentials"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return LoginResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }
}
