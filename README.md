# Steam LADHARI - A Spring Boot Microservices Project

## Description

This project is a microservices-based architecture built using Spring Boot, inspired by platforms like Steam. Its primary goal is to provide users with a platform to browse and purchase games. The architecture is designed for scalability, modularity, and ease of maintenance.

---

## Architectures

### Architecture Diagram

![Architecture Diagram](screens/spring_boot_architecture.png)

### Class Diagram

![Class Diagram](screens/spring_boot_classdiagram.png)

### Authentication and Authorization Flow

This document describes the authentication and authorization flow in a microservices architecture using an API Gateway. The flow ensures secure communication, proper validation of user credentials, and controlled access to microservices.

#### Flow Steps

1. **Login Request**:  
   The user sends a login request to the User Service, which processes the login and generates a JWT.

2. **API Gateway Validation**:  
   For all subsequent requests, the Front End includes the JWT in the authorization header. The API Gateway validates the JWT for authentication and authorization.

3. **JWT Validation**:  
   If the JWT is valid, the API Gateway forwards the request to the intended service. If invalid, a 401 Unauthorized response is returned.

4. **Request Forwarding**:  
   Upon successful JWT validation, the API Gateway forwards the request to the appropriate service for processing.

#### Summary

This flow is essential for ensuring secure access to microservices. It ensures that only authorized users can access services, and any invalid JWTs result in an appropriate error response.

![Authentication Flow](screens/architecture_auth.gif)


---

## Installation

Follow the instructions below to set up the project locally.

### Prerequisites

Make sure you have the following tools installed:

- **Java JDK 17** or later
- **Maven**
- **Docker** and **Docker Compose**
- **PostgreSQL**
- **MongoDB**
- **Angular CLI** (if working with the frontend)

### Steps

1. Clone the repository:

   ```bash
   git clone https://github.com/achrafladhari/UI_Spring
   cd UI_Spring
   ```

2. Build the Spring Boot microservices:

   ```bash
   cd <service-folder>
   mvn clean install
   ```

3. Start the required databases (MongoDB, PostgreSQL) using Docker Compose:

   ```bash
   docker-compose up -d
   ```

   This will start MongoDB and Mongo Express in Docker containers.

4. Run each microservice:

   ```bash
   cd <service-folder>
   mvn spring-boot:run
   ```

5. Example run the API Gateway:

   ```bash
   cd gateway
   mvn spring-boot:run
   ```

6. Run the Angular frontend (if applicable):

   ```bash
   cd UI_Spring
   npm install --force
   ng s
   ```

---

## Usage

### Authentication

All secured routes require a valid JWT token. Obtain the token by authenticating with the `/auth/login` endpoint in the `users` service. The token must be included in the `Authorization` header of all requests to secured endpoints.


### Endpoints

| Service            | Method           | Endpoint                                                 | Description                          | Secured |
| ------------------ | ---------------- | -------------------------------------------------------- | ------------------------------------ | ------- |
| **Category**       | POST, GET        | `localhost:8222/api/v1/category/admin`                   | Manage categories                    | Yes     |
|                    | GET, DELETE, PUT | `localhost:8222/api/v1/category/admin/{category-id}`     | Perform actions on specific category | Yes     |
| **Games**          | POST             | `localhost:8222/api/v1/game/admin`                       | Add a new game                       | Yes     |
|                    | POST             | `localhost:8222/api/v1/games/purchase`                   | Purchase a game                      | Yes     |
|                    | DELETE, PUT      | `localhost:8222/api/v1/game/admin/{game-id}`             | Manage a specific game               | Yes     |
|                    | GET              | `localhost:8222/api/v1/game/pagination`                  | Get paginated list of games          | No      |
|                    | GET              | `localhost:8222/api/v1/game/{game-id}`                   | Get game details                     | No      |
| **Library**        | GET, POST        | `localhost:8222/api/v1/library`                          | Access or modify the library         | Yes     |
|                    | DELETE           | `localhost:8222/api/v1/library/{username}`               | Delete a library by username         | Yes     |
|                    | PUT              | `localhost:8222/api/v1/library/purchase`                 | Update library with a purchase       | Yes     |
| **Orders**         | POST             | `localhost:8222/api/v1/orders`                           | Place an order                       | Yes     |
|                    | GET              | `localhost:8222/api/v1/orders/{username}`                | Get orders for a user                | Yes     |
| **Orders (Admin)** | GET              | `localhost:8222/api/v1/order/admin/{order-id}`           | Get specific order details (admin)   | Yes     |
|                    | GET              | `localhost:8222/api/v1/order/admin`                      | Get all orders (admin)               | Yes     |
| **Order Lines**    | GET              | `localhost:8222/api/v1/order-lines/order/{order-id}`     | Get order lines by order ID          | Yes     |
| **User Service**   | POST             | `localhost:8222/api/v1/auth/login`                       | Authenticate user                    | No      |
|                    | POST             | `localhost:8222/api/v1/auth/register`                    | Register a new user                  | No      |
|                    | GET              | `localhost:8222/api/v1/user/admin/exists/{user-id}`      | Check if user exists                 | Yes     |
|                    | GET              | `localhost:8222/api/v1/user/admin`                       | Get all users (admin)                | Yes     |
|                    | GET              | `localhost:8222/api/v1/user/admin/pagination`            | Get paginated list of users          | Yes     |
|                    | PUT              | `localhost:8222/api/v1/users/update/{user-id}`           | Update user details by ID            | Yes     |
|                    | PUT              | `localhost:8222/api/v1/users/update/username/{username}` | Update user details by username      | Yes     |
|                    | DELETE           | `localhost:8222/api/v1/users/{user-id}`                  | Delete user by ID                    | Yes     |
|                    | GET              | `localhost:8222/api/v1/users/{user-id}`                  | Get user details by ID               | Yes     |
|                    | GET              | `localhost:8222/api/v1/users/username/{user-id}`         | Get user details by username         | Yes     |

### SWAGGER TABLE

The table provides a concise overview of available Swagger endpoints for various services within a system. Each row lists a service name, the HTTP method (all being "SWAGGER" in this case), the specific endpoint URL, and whether the endpoint is secured. None of the endpoints are secured, as indicated in the "Secured" column. These endpoints correspond to the local environment, with URLs pointing to Swagger UI interfaces for managing Users, Games, Library, Orders, and Payments.

| Swager Service Name | Method  | Endpoint                                         | Secured |
| ------------------- | --------| ------------------------------------------------ | ------- |
| **SWGGER USER**     | SWAGGER | `localhost:8222/users/swagger-ui/index.html`     | NO      |
| **SWGGER Games**    | SWAGGER | `localhost:8222/games/swagger-ui/index.html`     | NO      |
| **SWGGER Library**  | SWAGGER | `localhost:8222/library/swagger-ui/index.html`   | NO      |
| **SWGGER Orders**   | SWAGGER | `localhost:8222/order/swagger-ui/index.html`     | NO      |
| **SWGGER Payments** | SWAGGER | `localhost:8222/payment/swagger-ui/index.html`   | NO      |


---


## Technologies

- [Spring Boot](https://spring.io/) - Backend framework
- [Spring Boot Microservices](https://spring.io/microservices) - Microservices
- [Spring Boot Cloud Gateway](https://spring.io/projects/spring-cloud-gateway) - Gateway
- [Spring Boot Reactive Programming](https://spring.io/reactive) - Reactive programming
- [PostgreSQL](https://www.postgresql.org/) - Relational database
- [MongoDB](https://www.mongodb.com/) - NoSQL database
- [Angular](https://angular.io/) - Frontend framework
- [Feign](https://spring.io/projects/spring-cloud-openfeign) - Declarative REST client
- [Eureka](https://spring.io/projects/spring-cloud-netflix) - Service discovery
- [JWT](https://jwt.io/) - Token-based authentication
- [Docker](https://docs.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

---

Achraf BEN CHEIKH LADHARI. © 2024 All rights reserved.
