# MoviXpress – Vehicle Rental System

![image](https://github.com/ClaudioCh-Dev/movi-xpress/blob/89f67c6d6ad150e32a43d98b3b509d9ec63b99fb/src/main/resources/static/images/movixpress-web.webp)

---

# Vehicle Rental System

This repository contains a complete vehicle rental management system built with Spring Boot.  
The application allows administrators and workers to efficiently handle client records, vehicle inventory, rental operations, and business configuration settings.

---

## Project Overview

The system is designed to streamline the workflow of small and medium vehicle rental businesses.  
It provides features for registering clients, managing vehicles, creating rental orders, assigning documents, tracking payments, and applying penalties when required.

---

## Features

- Client registration and management
- Vehicle inventory with image storage using Cloudinary
- Rental creation, management, and tracking
- Automatic penalty calculation
- User authentication and authorization (ADMIN, WORKER)
- Document type configuration (invoice, receipt, etc.)
- Company configuration and settings module
- Secure login using Spring Security

---

## Technologies Used

- Spring Boot (Security, JPA, Validation)
- MySQL
- Cloudinary
- Bootstrap
- Lombok

---

## Installation and Execution (Using Docker Compose)

To run the application and the database in an isolated and pre-configured way, use Docker Compose

1. **Clone the repository:**
   ```bash
   git clone https://github.com/ClaudioCh-Dev/movixpress.git
   cd movixpress
   ```
2. Configure Environment Variables:  
   The project requires Cloudinary credentials. See the Environment Configuration section for the .env file details
3. Build and Run with Docker Compose:  
   This command builds the Spring Boot application image and spins up the movixpress-app and movixpress-db containers
   ```bash
   docker-compose up --build
   ```
   The application will be available at `http://localhost:8080`

---

## Environment Configuration

The project includes an example environment file used to configure database access and Cloudinary credentials.

File: `.env.example`

Copy the file and rename it to:

```bash
.env
```

Fill your Cloudinary credentials:

```properties
CLOUDINARY_CLOUD_NAME=your_cloud_name
CLOUDINARY_API_KEY=your_api_key
CLOUDINARY_API_SECRET=your_api_secret
```

The `.env` file is ignored by Git and must not be committed.

---

## Database Initialization

The MySQL database is automatically initialized when launching Docker Compose.

- **Database Name:** movixpress
- **SQL Scripts:** The structure and initial data are located in the new directory:

```bash
/database/
    └── 01-schema.sql (Table Structure)
    └── 02-data.sql   (Initial Data)
```

### Main Tables

- moneda
- documentos
- marcas
- tipos
- clientes
- usuarios
- configuracion
- vehiculos
- alquiler

---

## Test Administrator User

```text
Username: admin
Password: contra123
```

Encrypted password:

```text
$2a$12$UXW2hk4pCL9LlyAUTBsaf.R3EF.NjIL8/X9YN4fld22WjMV15tnrS
```

---

## Project Structure

```text
.
├── src/
│   └── main/
│       ├── java/
│       └── resources/
│           ├── templates/
│           └── application.properties
├── database/
│   ├── 01-schema.sql
│   └── 02-data.sql
├── Dockerfile
└── docker-compose.yml
```

---


