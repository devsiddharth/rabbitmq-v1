package com.stschools.microservices.auth_service.controller;

import com.stschools.microservices.common_contracts.dto.request.LoginRequest;
import com.stschools.microservices.common_contracts.dto.request.RegisterRequest;
import com.stschools.microservices.auth_service.service.AuthService;
import com.stschools.microservices.common_contracts.dto.response.LoginResponse;
import com.stschools.microservices.common_contracts.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        UserResponse response = authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        return ResponseEntity.ok(authService.login(request));
    }
}