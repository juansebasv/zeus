# Zeus

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)

Zeus is a user management microservice built with Spring Boot 3.3.1 and Java 17.

## Version
1.0

## Author
Sebastian Vega

## Table of Contents
- [Prerequisites](#prerequisites)
- [Technologies and Dependencies](#technologies-and-dependencies)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)
- [Configuration](#configuration)
- [Documentation](#documentation)
- [Build and Run](#build-and-run)
- [Contributing](#contributing)
- [License](#license)

## Prerequisites
- Java 17
- Gradle 7.x or later

## Technologies and Dependencies
Zeus is built using the following technologies and major dependencies:

- **Java**: Version 17
- **Spring Boot**: Version 3.3.1
   - Spring Boot Starter
   - Spring Boot Starter Validation
   - Spring Boot Starter Web
   - Spring Boot Starter WebFlux
- **Gradle**:
   - Gradle Wrapper (included in the project)
   - Plugins:
      - org.springframework.boot: Version 3.3.1
      - io.spring.dependency-management: Version 1.1.5
- **Lombok**: Version 1.18.32 - Reduces boilerplate code
- **MapStruct**: Version 1.5.5.Final - Simplifies object mapping
   - MapStruct Spring Extensions: Version 1.1.1
- **SpringDoc OpenAPI**: Version 2.6.0 - API documentation
- **Micrometer**: For observability and metrics
- **Jakarta Validation API**: For bean validation
- **JUnit**: For unit testing
- **Spring Security Test**: For security testing in a Spring context

Additional tools and libraries:
- Swagger UI: For interactive API documentation
- Spring Boot Actuator: For application monitoring and management

## Getting Started
To get started with Zeus, clone the repository and build the project:

```bash
git clone https://github.com/juansebasv/zeus.git
cd zeus
./gradlew build
```

## Project Structure
Zeus follows a hexagonal architecture pattern, separating the core domain logic from external concerns.

## API Endpoints
Zeus provides the following REST endpoints for user management:

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/users/all` | GET | Retrieves a list of all users. |
| `/users/all/param/{param}/order/{order}` | GET | Retrieves a list of all users ordered by a specified parameter and direction. |
| `/users/{userId}/profile` | GET | Retrieves a user's profile by their ID. |
| `/users/{country}` | GET | Retrieves a list of users from a specified country. |
| `/users/{name}/{company}` | GET | Retrieves a list of users with the specified name and company. |
| `/users/email/{email}` | GET | Retrieves a list of users with email addresses in the specified domain. |

## Configuration
The application can be configured using the `application.properties` file. Key configurations include:

- Server port: 8080
- Context path: /
- Application name: zeus
- External API URLs for user data

## Documentation
API documentation is available via Swagger UI. Access it at:

```
http://localhost:8080/swagger-ui.html
```

The OpenAPI specification is available at:

```
http://localhost:8080/api-docs
```

## Build and Run
To build the project:

```bash
./gradlew build
```

To run the application:

```bash
./gradlew bootRun
```

The application will start on `http://localhost:8080`.

## API Usage Examples

### Get All Users

Request:
```
GET /users/all
```

Response:
```json
[
  {
    "id": "1",
    "name": "John Doe",
    "email": "john.doe@example.com",
    "country": "USA",
    "company": "Tech Corp"
  },
  {
    "id": "2",
    "name": "Jane Smith",
    "email": "jane.smith@example.com",
    "country": "Canada",
    "company": "Innovate Inc"
  }
]
```

### Get Users Ordered

Request:
```
GET /users/all/param/name/order/ASC
```

Response:
```json
[
  {
    "id": "2",
    "name": "Jane Smith",
    "email": "jane.smith@example.com",
    "country": "Canada",
    "company": "Innovate Inc"
  },
  {
    "id": "1",
    "name": "John Doe",
    "email": "john.doe@example.com",
    "country": "USA",
    "company": "Tech Corp"
  }
]
```

### Get User by ID

Request:
```
GET /users/1/profile
```

Response:
```json
{
  "id": "1",
  "name": "John Doe",
  "email": "john.doe@example.com",
  "country": "USA",
  "company": "Tech Corp"
}
```

### Get Users by Country

Request:
```
GET /users/USA
```

Response:
```json
[
  {
    "id": "1",
    "name": "John Doe",
    "email": "john.doe@example.com",
    "country": "USA",
    "company": "Tech Corp"
  },
  {
    "id": "3",
    "name": "Alice Johnson",
    "email": "alice.johnson@example.com",
    "country": "USA",
    "company": "Data Systems"
  }
]
```

### Get Users by Name and Company

Request:
```
GET /users/John/Tech%20Corp
```

Response:
```json
[
  {
    "id": "1",
    "name": "John Doe",
    "email": "john.doe@example.com",
    "country": "USA",
    "company": "Tech Corp"
  }
]
```

### Get Users by Email Domain

Request:
```
GET /users/email/example.com
```

Response:
```json
[
  {
    "id": "1",
    "name": "John Doe",
    "email": "john.doe@example.com",
    "country": "USA",
    "company": "Tech Corp"
  },
  {
    "id": "2",
    "name": "Jane Smith",
    "email": "jane.smith@example.com",
    "country": "Canada",
    "company": "Innovate Inc"
  }
]
```




## Contributing
Contributions to Zeus are welcome. Please feel free to submit a Pull Request.

## License
This project is licensed under the MIT License.