package com.stschools.microservices.auth_service.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.stschools.microservices.common_contracts.dto.response.UserAuthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiration}")
    private Long expiration;


    public String generateToken(UserAuthResponse user) {

        return JWT.create()

                .withSubject(user.getEmail())

                .withClaim("role", user.getRole().name())

                .withIssuer(issuer)

                .withIssuedAt(new Date())

                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))

                .sign(Algorithm.HMAC256(secret));

    }

    public String retrieveEmailFromToken(String token) {

        return JWT.require(Algorithm.HMAC256(secret))
                .withIssuer(issuer)
                .build()
                .verify(token)
                .getSubject();

    }

    public String retrieveRoleFromToken(String token) {

        return JWT.require(Algorithm.HMAC256(secret))
                .withIssuer(issuer)
                .build()
                .verify(token)
                .getClaim("role")
                .asString();

    }

    public boolean validateToken(String token) {

        try {

            JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer(issuer)
                    .build()
                    .verify(token);

            return true;

        } catch (Exception e) {

            return false;

        }

    }

}