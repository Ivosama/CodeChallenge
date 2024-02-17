# Project Name

This project was built to fulfill a code challenge.

## Table of Contents
- [Project Name](#project-name)
    - [Table of Contents](#table-of-contents)
    - [Description](#description)
    - [Key Technologies](#key-technologies-used)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Usage](#usage)

## Description

This project serves as a demonstration of skills and expertise in building a Spring Boot application, 
incorporating various technologies to meet the requirements of a given code challenge. 
The utilization of Swagger facilitates easy API exploration, while the choice of Java 17 and other technologies 
ensures a modern and efficient development process. The use of an H2 in-memory database allows for rapid prototyping
and testing, and the adherence to REST principles ensures a scalable and robust solution.

## Key Technologies used

Spring Boot: The project is built using the Spring Boot framework, providing a robust and efficient platform for developing Java-based applications.

Java 17: The latest version of Java is utilized to leverage the newest language features, improvements, and optimizations.

Swagger: API documentation is simplified and made interactive with Swagger, allowing developers to explore and understand the project's endpoints easily.

H2 In-Memory Database: For data storage during development, an H2 in-memory database is employed. This ensures a lightweight and fast database solution for testing and prototyping.

REST (Representational State Transfer): The project follows REST principles, enabling a scalable and stateless communication protocol for the web.

Lombok: To reduce boilerplate code, Lombok is used, enhancing the readability and maintainability of the codebase.

JDBC (Java Database Connectivity): JDBC is employed for database connectivity, allowing the Java application to interact with the H2 database.

JPA (Java Persistence API): JPA is utilized for querying the database using Java objects, providing a convenient and object-oriented approach to database interactions.

MVC (Model-View-Controller) Pattern: The project architecture follows the MVC pattern, ensuring a clear separation of concerns and promoting modularity.

HAL (Hypertext Application Language): A simple format that gives a consistent and easy way to hyperlink between resources in the API.

### Prerequisites

List any software, libraries, or other tools that users need to install before they can use your project.

```
- Java 17
- Maven
- Git
```

### Installation

Provide step-by-step instructions on how to install your project.

```
Example:
1. Clone the repository: `git clone https://github.com/Ivosama/CodeChallenge.git`
2. Open the project in intelliJ
3 (Option 1) Simply run 'CodeChallengeApplication' and navigate to 'http://localhost:8080/swagger-ui/index.html'
3 (Option 2) Alternatively you can run the tests located in 'src/test/java/com/idts/codechallenge/api'
```

## Usage

The database in being populated from the 'LoadDatabase' class. This means that the system can be tested from
http://localhost:8080/swagger-ui/index.html by using CRU operations (Delete was not implemented).

```
Example:
1. From http://localhost:8080/swagger-ui/index.html choose the GET operation /api/players/getById/{id} 
2. Press 'Try it out'
3. Input '1' in the id field
4. See the results (A Player named Mathias should have a game "LOL" associated)
5. Go to the PUT mapping /api/players/associateGameToPlayer/{id}/{game}
6. Input '1' in the id field and choose a different game
7. Confirm the new game has been assigned to Mathias.

 --- Testing wrong input (id) for /api/players/getById/{id} ---
 1. Input an index that does not yet exist, e.g. '100'.

 --- Testing wrong input (game) for /api/players/associateGameToPlayer/{id}/{game} ---
1. Go to Postman while the application is running and make a request from here.
```

## Acknowledgments

The documentation, projects and examples available in https://spring.io/learn. Additionally, Mike Kelly for his work in https://stateless.group/hal_specification.html