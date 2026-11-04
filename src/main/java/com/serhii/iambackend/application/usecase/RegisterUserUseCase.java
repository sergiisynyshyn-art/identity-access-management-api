package com.serhii.iambackend.application.usecase;

import com.serhii.iambackend.application.dto.RegisterUserRequest;
import com.serhii.iambackend.application.dto.UserResponse;

public interface RegisterUserUseCase {

    UserResponse register(RegisterUserRequest request);

}