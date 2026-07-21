package com.stschools.microservices.user_service.service;

import com.stschools.microservices.user_service.dto.CreateUserRequest;
import com.stschools.microservices.user_service.dto.UserResponse;
import com.stschools.microservices.user_service.entity.User;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    UserResponse getUserById(Long id);

    User getUserByEmail(String email);

}