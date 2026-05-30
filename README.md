🚗 Car Marketplace

A microservice-based vehicle marketplace application developed with Spring Boot, React, and PostgreSQL.

The platform allows users to register, authenticate using JWT, create vehicle listings, manage their own vehicles, and browse available listings. The project is designed using a microservice architecture to demonstrate modern backend development practices, service-to-service communication, API Gateway usage, and secure authentication mechanisms.

---

🏗️ Architecture

Auth Service – Handles user registration, authentication, and JWT generation.

Customer Service – Manages customer information and profile operations.

Car Service – Handles vehicle listing creation,updating, retrieval, and deletion.

---

🚀 Features

- User Registration and Login

- JWT-Based Authentication and Authorization

- Vehicle Listing Management

- User Profile Management

- Vehicle Creation and Deletion

- Service-to-Service Communication using OpenFeign

- RESTful API Design

- Exception Handling and Validation

---

🛠️ Technologies

- Java 21
- Spring Boot
- Spring Security
- Spring Cloud Gateway
- OpenFeign
- JWT
- PostgreSQL
- Maven

---

📦 Project Goal

The primary goal of this project is to showcase backend development skills, microservice architecture design, secure authentication, and full-stack integration using modern Java and React technologies.

---

📡 API Endpoints

🔐 Auth Service

---

- POST	/auth/register	Register a new user
- POST	/auth/login	Authenticate user and generate JWT token

👤 Customer Service

---

- POST	/api/v1/customers	Create a customer
- GET	/api/v1/customers	Get all customers (paginated)
- GET	/api/v1/customers/{id}	Get customer by ID
- GET	/api/v1/customers/by-email?email={email}	Get customer by email
- GET	/api/v1/customers/by-phone?phone={phone}	Check if phone number exists
