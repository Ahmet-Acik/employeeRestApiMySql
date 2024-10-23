# Employee Management System

## Overview

The Employee Management System is a Spring Boot application designed to manage employee records. It provides functionalities to add, update, delete, and retrieve employee details. The application uses a RESTful API architecture and integrates with a database to store employee information.

## Features

- Add a new employee
- Update existing employee details
- Delete an employee
- Retrieve employee details by ID
- Retrieve all employees

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven
- JUnit 5
- Mockito
- Lombok
- H2 Database (for testing)

## Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher

## Getting Started

### Clone the Repository

```sh
git clone https://github.com/Ahmet-Acik/EmployeeManagementSystem.git
cd EmployeeManagementSystem
```

### Build the Project

```sh
mvn clean install
```

### Run the Application

```sh
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

## API Endpoints

### Add a New Employee

- **URL:** `/api/employees`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "name": "John",
    "surname": "Doe",
    "email": "john.doe@example.com",
    "jobTitle": "Developer",
    "phone": "1234567890",
    "imageUrl": "http://example.com/image.jpg",
    "employeeCode": "EMP001"
  }
  ```
- **Response:**
  ```json
  {
    "id": 1,
    "name": "John",
    "surname": "Doe",
    "email": "john.doe@example.com",
    "jobTitle": "Developer",
    "phone": "1234567890",
    "imageUrl": "http://example.com/image.jpg",
    "employeeCode": "EMP001"
  }
  ```

### Get All Employees

- **URL:** `/api/employees`
- **Method:** `GET`
- **Response:**
  ```json
  [
    {
      "id": 1,
      "name": "John",
      "surname": "Doe",
      "email": "john.doe@example.com",
      "jobTitle": "Developer",
      "phone": "1234567890",
      "imageUrl": "http://example.com/image.jpg",
      "employeeCode": "EMP001"
    }
  ]
  ```

### Get Employee by ID

- **URL:** `/api/employees/{id}`
- **Method:** `GET`
- **Response:**
  ```json
  {
    "id": 1,
    "name": "John",
    "surname": "Doe",
    "email": "john.doe@example.com",
    "jobTitle": "Developer",
    "phone": "1234567890",
    "imageUrl": "http://example.com/image.jpg",
    "employeeCode": "EMP001"
  }
  ```

### Update Employee

- **URL:** `/api/employees/{id}`
- **Method:** `PUT`
- **Request Body:**
  ```json
  {
    "name": "John",
    "surname": "Doe",
    "email": "john.doe@example.com",
    "jobTitle": "Senior Developer",
    "phone": "1234567890",
    "imageUrl": "http://example.com/image.jpg",
    "employeeCode": "EMP001"
  }
  ```
- **Response:**
  ```json
  {
    "id": 1,
    "name": "John",
    "surname": "Doe",
    "email": "john.doe@example.com",
    "jobTitle": "Senior Developer",
    "phone": "1234567890",
    "imageUrl": "http://example.com/image.jpg",
    "employeeCode": "EMP001"
  }
  ```

### Delete Employee

- **URL:** `/api/employees/{id}`
- **Method:** `DELETE`
- **Response:** `204 No Content`

## Running Tests

To run the tests, use the following command:

```sh
mvn test
```

## Exception Handling

The application includes a global exception handler to manage validation errors and custom exceptions.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Contact

For any inquiries or issues, please contact Ahmet Acik at [a.acik@icloud.com].