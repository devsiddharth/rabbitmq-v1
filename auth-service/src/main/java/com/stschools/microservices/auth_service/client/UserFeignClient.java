package com.stschools.microservices.auth_service.client;

import com.stschools.microservices.common_contracts.dto.request.CreateUserRequest;
import com.stschools.microservices.common_contracts.dto.response.UserAuthResponse;
import com.stschools.microservices.common_contracts.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "user-service",
        path = "/api/v1/users"
)
public interface UserFeignClient {

    @PostMapping
    UserResponse createUser(
            @RequestBody CreateUserRequest request);

    @GetMapping("/auth/{email}")
    UserAuthResponse getUserForAuthentication(
            @PathVariable String email);

}