package com.serhii.iambackend.application.usecase;

import com.serhii.iambackend.application.dto.RegisterUserRequest;
import com.serhii.iambackend.application.dto.UserResponse;
import com.serhii.iambackend.domain.exception.UserAlreadyExistsException;
import com.serhii.iambackend.domain.model.User;
import com.serhii.iambackend.domain.ports.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public RegisterUserUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public UserResponse register(RegisterUserRequest request) {

        if (userRepositoryPort.existsByEmail(request.getEmail())) {

            throw new UserAlreadyExistsException();
        }
        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .enabled(true)
                .build();
        User savedUser = userRepositoryPort.save(user);

        return UserResponse.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .build();
    }

}