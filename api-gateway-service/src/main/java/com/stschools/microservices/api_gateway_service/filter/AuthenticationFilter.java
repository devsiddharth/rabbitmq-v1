package com.stschools.microservices.api_gateway_service.filter;

import com.stschools.microservices.api_gateway_service.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter implements GlobalFilter, Ordered {

    private final JwtUtil jwtUtil;
    private final RouteValidator routeValidator;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {

        System.out.println("========== Authentication Filter ==========");

        String path = exchange.getRequest().getURI().getPath();
        System.out.println("Path : " + path);

        System.out.println("Request Path: " + path);
        System.out.println("Is Secured: " + routeValidator.isSecured(path));

        if(!routeValidator.isSecured(path)){

            return chain.filter(exchange);

        }

        System.out.println("==============================");
        System.out.println("AuthenticationFilter Executed");

        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        System.out.println("Authorization Header: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();

        }

        String token = authHeader.substring(7);

        System.out.println("Token : " + token);
        System.out.println("Valid : " + jwtUtil.validateToken(token));

        if (!jwtUtil.validateToken(token)) {

            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();

        }

        String email = jwtUtil.retrieveEmailFromToken(token);

        String role = jwtUtil.retrieveRoleFromToken(token);

        System.out.println("Email : " + email);
        System.out.println("Role : " + role);
        System.out.println("Forwarding request...");

        ServerHttpRequest request =
                exchange.getRequest()
                        .mutate()
                        .header("X-Authenticated-User", email)
                        .header("X-Authenticated-Role", role)
                        .build();

        return chain.filter(exchange.mutate().request(request).build());

    }

    @Override
    public int getOrder() {
        return -1;
    }
}