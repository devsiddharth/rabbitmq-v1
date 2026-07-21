package com.stschools.microservices.user_service.service.impl;

import com.stschools.microservices.user_service.dto.CreateUserRequest;
import com.stschools.microservices.user_service.dto.UserResponse;
import com.stschools.microservices.user_service.entity.User;
import com.stschools.microservices.user_service.enums.Role;
import com.stschools.microservices.user_service.exception.EmailAlreadyExistsException;
import com.stschools.microservices.user_service.exception.PasswordMismatchException;
import com.stschools.microservices.user_service.repository.UserRepository;
import com.stschools.microservices.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(CreateUserRequest request) {

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new PasswordMismatchException("Passwords do not match");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword()) // Temporary
                .role(Role.USER)
                .build();

        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .phoneNumber(savedUser.getPhoneNumber())
                .role(savedUser.getRole())
                .build();
    }

    @Override
    public UserResponse getUserById(Long id) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}