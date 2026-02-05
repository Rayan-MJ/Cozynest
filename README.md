# Cozynest (Backend)

Cozynest is a website where users can discover books, read them, and share reviews with each other. This repository contains the backend for Cozynest, implemented in Spring Boot with Java 17. It serves as the API and message-processing backend for a React frontend, using RabbitMQ for asynchronous messaging. The project demonstrates backend engineering skills including REST API design, JWT-based authentication, messaging with RabbitMQ, data persistence with JPA, dependency and build management with Maven, and testing.

## Table of contents

- [What is it](#what-is-it)
- [Key features & highlights](#key-features--highlights)
- [Tech stack](#tech-stack)
- [Architecture overview](#architecture-overview)
- [Prerequisites](#prerequisites)
- [Run locally (dev)](#run-locally-dev)
- [RabbitMQ usage](#rabbitmq-usage)
- [API examples](#api-examples)
- [Testing](#testing)

---

## What is it

Cozynest is the backend for a web application (React frontend). It exposes REST endpoints for the frontend and processes asynchronous work via RabbitMQ (e.g., notifications, background processing, or event handling). The design focuses on clean layering (controller -> service -> repository), JWT-based authentication, and message-driven components.

---

## Key features & highlights

- Java 17 + Spring Boot
- RESTful API endpoints (controllers, DTOs, validation)
- Spring Data JPA for persistence (H2 for dev, MySQL supported)
- Spring Security with JWT authentication
- RabbitMQ integration (publish/consume messages)
- Unit and integration tests
- Maven build & wrapper included (`mvnw`)
- Docker-enabled (recommended for demo with RabbitMQ & MySQL)

---

## Tech stack

- Language: Java 17
- Frameworks: Spring Boot (Web, Security, Data JPA, AMQP)
- Messaging: RabbitMQ
- Persistence: H2 (dev), MySQL (prod)
- Build: Maven (wrapper included)
- Testing: JUnit, Mockito (example)
- Frontend: React (separate project) — this repo is backend only

---

## Architecture overview

- Controllers: HTTP REST API surface used by the React frontend
- Services: Business logic, validation, orchestrating repositories and messaging
- Repositories: Spring Data JPA interfaces for DB access
- Messaging: Producers publish domain events to RabbitMQ; Consumers listen on queues and handle background tasks
- Security: JWT issued at login, validated by a filter for protected endpoints

A typical request flow:
1. React frontend calls REST endpoint.
2. Controller validates and forwards to service.
3. Service either persists data and/or publishes an event to RabbitMQ.
4. A background consumer processes messages from RabbitMQ (e.g., send emails, updates).

---

## Prerequisites

- JDK 17
- Maven (optional — `./mvnw` is included)
- Docker & Docker Compose (recommended for quick demo)
- RabbitMQ (can run via Docker compose below)
- (Optional) MySQL if you want to run against a MySQL database

---
## Run locally (dev)

1. Clone the repo:
   ```bash
   git clone https://github.com/<your-username>/Cozynest.git
   cd Cozynest
   ```

2. Start a local RabbitMQ (or use Docker as shown below). For quick dev, start RabbitMQ with Docker:
   ```bash
   docker run -d --hostname rabbit --name cozynest-rabbit -p 5672:5672 -p 15672:15672 rabbitmq:3-management
   ```
   Visit http://localhost:15672 (default user `guest`/`guest`) to confirm.

3. Run the app:
   - With Maven wrapper:
     ```bash
     ./mvnw spring-boot:run
     ```
   - OR build and run:
     ```bash
     ./mvnw clean package -DskipTests
     java -jar target/cozynest-0.0.1-SNAPSHOT.jar
     ```

4. Open API (default): http://localhost:8080
   - If the React frontend runs separately, make sure CORS is configured and point the frontend to the backend URL.

---

## RabbitMQ usage

- Purpose: async background jobs, event-driven flows, decoupling frontend request latency from longer-running tasks.
- Example queue names (replace with project-specific names):
  - `cozynest.notifications`
  - `cozynest.tasks`
- Example producer (pseudocode):
  ```java
  rabbitTemplate.convertAndSend("cozynest.exchange", "cozynest.routing.key", payload);
  ```
- Example consumer (pseudocode):
  ```java
  @RabbitListener(queues = "${RABBITMQ_QUEUE_MY_QUEUE}")
  public void handleMessage(MyMessageDto dto) {
      // process
  }
  ```
- Useful for: sending emails, generating reports, notifying other microservices, rate-limited processing.

---

## API examples

Below are sample endpoints and requests. Replace with real endpoints from your controllers.

1. Authentication (login)
   ```bash
   curl -X POST http://localhost:8080/api/auth/login \
     -H "Content-Type: application/json" \
     -d '{"username":"alice","password":"password"}'
   ```
   Response should return a JWT:
   ```json
   { "token": "eyJhbGciOi..." }
   ```

2. Protected resource example:
   ```bash
   curl -H "Authorization: Bearer <JWT>" http://localhost:8080/api/users/me
   ```

3. Publish message to queue:
   ```bash
   curl -X POST http://localhost:8080/api/tasks \
     -H "Content-Type: application/json" \
     -d '{"taskType":"REPORT","payload":{...}}'
   ```
---

## Testing

- Unit tests: run with Maven
  ```bash
  ./mvnw test
  ```
