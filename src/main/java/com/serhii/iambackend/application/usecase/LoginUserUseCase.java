package com.serhii.iambackend.application.usecase;

import com.serhii.iambackend.application.dto.LoginRequest;
import com.serhii.iambackend.application.dto.LoginResponse;

public interface LoginUserUseCase {

    LoginResponse login(LoginRequest request);

}