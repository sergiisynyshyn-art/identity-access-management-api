package application.usecase;

import application.dto.RegisterUserRequest;
import application.dto.UserResponse;

public interface RegisterUserUseCase {

    UserResponse register(RegisterUserRequest request);

}