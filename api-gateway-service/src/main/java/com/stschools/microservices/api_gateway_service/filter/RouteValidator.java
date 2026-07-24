package com.stschools.microservices.api_gateway_service.filter;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(

            "/api/v1/auth/login",
            "/api/v1/auth/register"

    );

    public boolean isSecured(String path){

        return openApiEndpoints
                .stream()
                .noneMatch(path::startsWith);

    }

}