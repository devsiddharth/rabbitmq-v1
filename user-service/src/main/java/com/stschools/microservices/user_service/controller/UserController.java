package com.stschools.microservices.user_service.controller;

import com.stschools.microservices.common_contracts.dto.request.CreateUserRequest;
import com.stschools.microservices.common_contracts.dto.response.UserAuthResponse;
import com.stschools.microservices.common_contracts.dto.response.UserResponse;
import com.stschools.microservices.user_service.entity.User;
import com.stschools.microservices.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody CreateUserRequest request){

        UserResponse response = userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(
            @PathVariable String email) {

        User user = userService.getUserByEmail(email);

        UserResponse response = modelMapper.map(user, UserResponse.class);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/auth/{email}")
    public ResponseEntity<UserAuthResponse> getUserForAuthentication(
            @PathVariable String email) {

        User user = userService.getUserByEmail(email);

        UserAuthResponse response =
                modelMapper.map(user, UserAuthResponse.class);

        return ResponseEntity.ok(response);
    }

}
