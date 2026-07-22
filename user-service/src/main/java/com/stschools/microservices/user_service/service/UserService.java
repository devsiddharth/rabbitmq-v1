package com.stschools.microservices.user_service.service;

import com.stschools.microservices.common_contracts.dto.request.CreateUserRequest;
import com.stschools.microservices.common_contracts.dto.response.UserResponse;
import com.stschools.microservices.user_service.entity.User;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    UserResponse getUserById(Long id);

    User getUserByEmail(String email);

}