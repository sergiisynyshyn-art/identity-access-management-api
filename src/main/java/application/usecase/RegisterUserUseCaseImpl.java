package application.usecase;

import application.dto.RegisterUserRequest;
import application.dto.UserResponse;
import domain.exception.UserAlreadyExistsException;
import domain.ports.UserRepositoryPort;

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

        return null;
    }
}