# Expense Tracker App - Back End

This repository contains the back-end code for the Expense Tracker App, a school project for the Database Systems course (ISE 305) at Istanbul Technical University.

## Project Overview

The Expense Tracker App is a full-stack application designed to help users track their daily expenses and view monthly spending summaries. The project consists of two main parts:
- **Backend**: Developed with Spring Boot
- **Frontend**: Developed with React

## Course Information

- **Course**: Database Systems (ISE 305)
- **Institution**: Istanbul Technical University

## Project Description

You are expected to choose a topic, analyze the problem, the data, and the relationships of the data regarding the problem. Then you need to generate the normalized data model, implement the model in any relational database management system you choose, and develop an application that makes use of this database. You can use any programming language you want, with the limitation that you are not allowed to use any ORM framework.

### Project Requirements

- **Minimum Tables**: 8-10 tables should exist in the final database schema.
- **Submission Requirements**:
  - Project Report
  - Source Codes
  - Database Dump (SQL Export)

## Project Components

### Backend

- **Technologies**: Spring Boot, JDBC
- **Features**:
  - RESTful API endpoints for managing users, expenses, incomes, and payment methods
  - Data persistence in a relational database without using any ORM framework

## Setup Instructions

### Prerequisites

- Java Development Kit (JDK) installed
- Maven installed
- Database server running (MySQL)


### Running the Backend

1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/expense-tracker-app-back-end.git
    ```

2. Navigate to the project directory:
    ```bash
    cd expense-tracker-app-back-end
    ```

3. Configure the database connection in `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/your-database
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    spring.jpa.hibernate.ddl-auto=update
    ```

4. Build the project:
    ```bash
    mvn clean install
    ```

5. Run the application:
    ```bash
    mvn spring-boot:run
    ```

6. The backend server will start on `http://localhost:8080`.
