# Task Management Application

This is a Spring Boot application for managing tasks. It provides CRUD operations for tasks and includes an API endpoint for sending email notifications.

## Features

- Create, read, update, and delete tasks.
- Search and filter tasks based on various criteria.
- Send email notifications for upcoming task deadlines or important updates.

## Technologies Used

- Spring Boot
- Spring Security
- Jwt
- Spring Data JPA
- JavaMailSender (for sending emails)
- H2 Database (for storing task data)

## Getting Started

### Prerequisites

- Java Development Kit (JDK) version 11
- Apache Maven
- Git

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/Shrouk1212/BM.git

2. Navigate to the project directory:
   ```bash
   cd task-management

3. Build the project using Maven:
   ```bash
   mvn clean install
   
4. Run the application:
   ```bash
   java -jar target/demo-0.0.1-SNAPSHOT.jar


### Usage

- Access the application at: http://localhost:8080
- Use the provided API endpoints for managing tasks and sending email notifications.

### API Endpoints
1. Task Management Endpoints:
POST /tasks: Create a new task.
GET /tasks: Get all tasks with pagination support.
GET /tasks/{id}: Get a task by its ID.
PUT /tasks/{id}: Update a task by its ID.
DELETE /tasks/{id}: Delete a task by its ID.
2. Search Endpoints:
GET /tasks/searchByTitle: Search tasks by title.
GET /tasks/searchByDescription: Search tasks by description.
GET /tasks/searchByStatus: Search tasks by status.
GET /tasks/searchByDueDate: Search tasks by due date.
3. Filtering Endpoint:
GET /tasks/filter: Filter tasks based on various criteria such as title, description, status, priority, and due date.
4. Email Notification Endpoint:
POST /send-email: Send an email notification. It accepts a JSON request body with the recipient email address, subject, and content.
5. Authentication Endpoint:
POST /authenticate: Authenticate a user by providing their username and password. Upon successful authentication, it returns a JWT token in the response body.

### Configuration
Configure email service provider credentials in application.properties file.



