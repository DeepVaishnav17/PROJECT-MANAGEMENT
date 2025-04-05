
# 🛠️ Project Management REST API (Spring Boot)

A Spring Boot RESTful API for project management with secure, role-based access using Spring Security and MySQL as the database. Built with three roles — Admin, Manager, and User — to demonstrate clean endpoint protection and scalable backend structure.

---

## 🔐 Role-Based Access

| Role    | Access Levels                    |
|---------|----------------------------------|
| Admin   | Full control over all resources  |
| Manager | Manage assigned projects/tasks   |
| User    | View and update assigned tasks   |

---

## 🚀 Features

- Spring Security-based authentication
- Role-based URL access (Admin, Manager, User)
- RESTful endpoints for managing users, tasks, and projects
- MySQL integration using Spring Data JPA
- **Postman collection JSON provided for testing all operations**
- Clean architecture for future JWT/token integration

---

## 🧪 Test Credentials

| Username | Password     | Role    |
|----------|-------------|---------|
| admin    | admin@123    | ADMIN   |
| manager  | manager@123  | MANAGER |
| user     | user@123     | USER    |

---

## 🛠️ Tech Stack

- **Java 17+**
- **Spring Boot**
- **Spring Security**
- **Spring Data JPA**
- **MySQL**
- **Maven**
- **Postman (for API testing)**

---
