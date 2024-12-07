# GenericBase Project

This project provides a generic foundation for entity management in a Java application using CQRS and CRUD patterns. It includes various handlers for performing CRUD operations on entities, along with command and query structures to facilitate flexible and efficient data interaction. This is a library available on [Maven Central](https://search.maven.org/).

## Features

- **Generic Handlers:** Base classes for handling entity operations such as create, read, update, and delete (CRUD).
- **CQRS Support:** Commands and queries to separate read and write operations.
- **Model Mapping:** Uses `ModelMapper` for mapping between domain and database entities.
- **Validation Support:** Built-in validation support for commands.
- **Exception Handling:** Custom exceptions for handling common errors like entity not found and pagination bounds.

## Project Structure

- `cqrs.handlers.commands`: Contains handlers responsible for creating, updating, and deleting entities.
- `cqrs.handlers.queries`: Contains handlers for retrieving entities based on query parameters.
- `cqrs.domain`: Contains the base domain class for business logic and entity manipulation.
- `cqrs.entities`: Contains base entity classes representing database records.
- `cqrs.exceptions`: Contains custom exceptions to handle various application errors (e.g., entity not found).
- `cqrs.commands`: Defines command classes for entity operations like create, update, and delete.
- `cqrs.queries`: Defines query classes for retrieving data with sorting and pagination.
- `cqrs.outputs`: Contains output classes for formatting the results of entity operations.
- `cqrs.interfaces`: Defines key interfaces for repositories, validators, and command/query handlers.

## Requirements

- Java 17 or higher
- Spring Boot
- ModelMapper
- Lombok

## Setup

This library is available on Maven Central. To add it to your project, include the following dependency in your `pom.xml`:

```xml
<dependency>
    <groupId>com.justinligny.cqrs</groupId>
    <artifactId>cqrs-core</artifactId>
    <version>1.0.0</version>
</dependency>
