package com.stschools.microservices.auth_service.service;

import com.stschools.microservices.common_contracts.dto.request.LoginRequest;
import com.stschools.microservices.common_contracts.dto.request.RegisterRequest;
import com.stschools.microservices.common_contracts.dto.response.LoginResponse;
import com.stschools.microservices.common_contracts.dto.response.UserResponse;

public interface AuthService {

    UserResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

}