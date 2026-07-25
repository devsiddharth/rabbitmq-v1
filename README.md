# Spring Boot Microservices with JWT Authentication & Role-Based Authorization

A production-inspired microservices application built using Spring Boot and Spring Cloud that demonstrates secure authentication, role-based authorization, API Gateway, and service discovery.

The project is being developed incrementally following real-world backend development practices, with each service designed and implemented from scratch while following clean architecture and modular design principles.

---

## Architecture

```
                   Client
                      │
                      ▼
               API Gateway
                      │
          JWT Authentication
          Role Validation
                      │
      ┌───────────────┴───────────────┐
      ▼                               ▼
 Auth Service                  User Service
      │                               │
      └───────────────┬───────────────┘
                      ▼
                Eureka Server
```

---

## Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JWT Authentication
- Role-Based Authorization
- Spring Cloud Gateway
- Eureka Discovery Server
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok

---

## Features

### Authentication

- User Registration
- User Login
- BCrypt Password Encryption
- JWT Token Generation
- JWT Validation
- Stateless Authentication

### Authorization

- Role-Based Access Control (RBAC)
- Protected APIs
- Public and Private Route Configuration
- Spring Security Filter Chain

### API Gateway

- Single Entry Point
- JWT Validation
- Request Routing
- Centralized Security

### User Service

- User Management
- Database Persistence
- Validation
- Secure Endpoints

### Service Discovery

- Eureka Server
- Automatic Service Registration
- Dynamic Service Discovery

---

## Current Microservices

| Service | Status |
|----------|--------|
| Eureka Server | ✅ Completed |
| API Gateway | ✅ Completed |
| Auth Service | ✅ Completed |
| User Service | ✅ Completed |

---

## Security Flow

1. User registers through the Auth Service.
2. Password is encrypted using BCrypt.
3. User logs in.
4. JWT Access Token is generated.
5. Client sends JWT in every request.
6. API Gateway validates the token.
7. User role is extracted from the JWT.
8. Requests are forwarded only if the user has the required authorization.

---

## What This Project Demonstrates

- Microservices Architecture
- API Gateway Pattern
- Service Discovery
- JWT Authentication
- Role-Based Authorization
- Spring Security
- REST API Development
- Database Integration
- Clean Code Principles
- Layered Architecture
- Constructor-Based Dependency Injection

---

## Roadmap

### Completed

- ✅ Eureka Discovery Server
- ✅ API Gateway
- ✅ JWT Authentication
- ✅ Role-Based Authorization
- ✅ User Service
- ✅ Auth Service

### In Progress

- 🔄 Notification Service using RabbitMQ

### Planned

- Docker & Docker Compose
- Config Server
- Centralized Logging
- Distributed Tracing
- Resilience4j Circuit Breaker
- API Documentation (OpenAPI / Swagger)
- CI/CD Pipeline
- Cloud Deployment
- Kubernetes (Future Enhancement)

---

## Repository Structure

```
microservices
│
├── eureka-server
├── api-gateway
├── auth-service
├── common-contracts
├── user-service
├── notification-service
└── README.md
```

---

## Future Vision

The objective is to evolve this repository into a complete production-style microservices ecosystem by gradually integrating asynchronous communication, containerization, cloud deployment, observability, and DevOps practices.

---

## Author

**Siddhartha G**

Java Full Stack Developer
