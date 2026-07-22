package com.stschools.microservices.auth_service.service.impl;


import com.stschools.microservices.auth_service.client.UserFeignClient;
import com.stschools.microservices.common_contracts.dto.request.CreateUserRequest;
import com.stschools.microservices.common_contracts.dto.request.LoginRequest;
import com.stschools.microservices.common_contracts.dto.request.RegisterRequest;
import com.stschools.microservices.auth_service.service.AuthService;
import com.stschools.microservices.common_contracts.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserFeignClient userFeignClient;

    private final ModelMapper modelMapper;

    @Override
    public UserResponse register(RegisterRequest request) {

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        CreateUserRequest createUserRequest =
                modelMapper.map(request, CreateUserRequest.class);

        return userFeignClient.createUser(createUserRequest);

    }

    @Override
    public String login(LoginRequest request) {

        return null;
    }

}