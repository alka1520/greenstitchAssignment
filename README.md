# greenstitchAssignment

# Login and Signup REST API

This project provides a backend implementation of a login and signup REST API with security features and JSON Web Tokens (JWT) for authentication. The API allows users to create new accounts, authenticate using their credentials, and access protected resources.

## Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Spring Security
- JSON Web Tokens (JWT)
- lombok

## Getting Started

To get started with the project, follow the instructions below:

1. Clone the repository to your local machine.
2. Ensure you have Java Development Kit (JDK) 8 or higher installed.
3. Build the project using your preferred build tool (Maven or Gradle).
4. Configure the H2 database connection in the `application.properties` file.
5. Run the application.

## API Endpoints

The API provides the following endpoints:

### Signup

Endpoint: `POST /localhost:8080/signUp`

Creates a new user account.

Request Payload:

```json
{
  "username": "john_doe",
  "email": "john.doe@example.com",
  "password": "P@ssw0rd"
}
```

Response:

```json
{
  "message": "Registered Successfully."
}
```

### Login

Endpoint: `POST /localhost:8080/logIn`

Authenticates a user and generates a JWT token.

Request Payload:

```json
{
  "username": "john_doe",
  "password": "P@ssw0rd"
}
```

Response:

```json
{
  "username": "john_doe",
  "email": "john.doe@example.com",
  "password": "P@ssw0rd"
}
```

and token in Header with "Bearer" key

### Protected Resource

Endpoint: `GET /protected-resource`

Retrieves a protected resource.

Request Header: Token
