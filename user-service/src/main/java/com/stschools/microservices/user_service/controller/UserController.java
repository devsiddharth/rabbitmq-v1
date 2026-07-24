package com.stschools.microservices.user_service.controller;

import com.stschools.microservices.common_contracts.dto.request.CreateUserRequest;
import com.stschools.microservices.common_contracts.dto.response.UserAuthResponse;
import com.stschools.microservices.common_contracts.dto.response.UserResponse;
import com.stschools.microservices.user_service.entity.User;
import com.stschools.microservices.user_service.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<User> getByEmail(
            @PathVariable String email,
            HttpServletRequest request) {

        System.out.println("Reached User Service");
        System.out.println("Header Authorization = "
                + request.getHeader("Authorization"));

        System.out.println("X-Authenticated-User = "
                + request.getHeader("X-Authenticated-User"));

        System.out.println("X-Authenticated-Role = "
                + request.getHeader("X-Authenticated-Role"));

        return ResponseEntity.ok(userService.getUserByEmail(email));
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
