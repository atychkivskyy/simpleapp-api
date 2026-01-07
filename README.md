# SimpleApp API

A RESTful API built with Spring Boot 4.0 for managing products. This application serves as the backend component of the SimpleApp ecosystem and is designed to be deployed using GitOps practices.

## Architecture

- **Framework**: Spring Boot 4.0.1
- **Language**: Java 21
- **Database**: PostgreSQL 18
- **Migration**: Flyway
- **Container**: Docker with multi-stage build
- **Deployment**: GitOps via [simpleapp-gitops](https://github.com/atychkivskyy/simpleapp-gitops)

## Features

- RESTful API for product management (CRUD operations)
- Health checks and metrics via Spring Boot Actuator
- Database versioning with Flyway migrations
- Docker containerization with optimized JVM settings
- Kubernetes-ready health probes (liveness/readiness)
- Environment-based configuration

## Quick Start

### Prerequisites

- Java 21+
- Maven 3.9+
- Docker & Docker Compose (optional)
- PostgreSQL 18 (or use Docker Compose)

### Running with Docker Compose

The easiest way to run the application locally:

```bash
docker-compose up -d
```

This will start:
- PostgreSQL database on port `5432`
- SimpleApp API on port `8082`

### Running Locally

1. **Start PostgreSQL** (if not using Docker):
   ```bash
   # Ensure PostgreSQL is running with the following credentials:
   # Database: simpleapp_db
   # Username: simpleapp
   # Password: simpleapppass
   ```

2. **Build and run the application**:
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Or build a JAR**:
   ```bash
   ./mvnw clean package -DskipTests
   java -jar target/simpleapp-0.0.1-SNAPSHOT.jar
   ```

## API Endpoints

### Products API

| Method | Endpoint              | Description          |
|--------|-----------------------|----------------------|
| GET    | `/api/v1/products`    | List all products    |
| GET    | `/api/v1/products/{id}` | Get product by ID  |
| POST   | `/api/v1/products`    | Create a new product |
| PUT    | `/api/v1/products/{id}` | Update a product   |
| DELETE | `/api/v1/products/{id}` | Delete a product   |

### Info API

| Method | Endpoint         | Description                    |
|--------|------------------|--------------------------------|
| GET    | `/api/v1/info`   | Application info and version   |

### Actuator Endpoints

| Endpoint                  | Description              |
|---------------------------|--------------------------|
| `/actuator/health`        | Health status            |
| `/actuator/health/liveness`  | Liveness probe        |
| `/actuator/health/readiness` | Readiness probe       |
| `/actuator/info`          | Application information  |
| `/actuator/metrics`       | Application metrics      |
| `/actuator/prometheus`    | Prometheus metrics       |

## Product Schema

```json
{
  "id": 1,
  "name": "Laptop",
  "description": "High-performance laptop",
  "price": 999.99,
  "createdAt": "2026-01-07T10:00:00",
  "updatedAt": "2026-01-07T10:00:00"
}
```

### Validation Rules

- `name`: Required, cannot be blank
- `price`: Required, must be positive

## Configuration

The application can be configured using environment variables:

| Variable        | Default        | Description            |
|-----------------|----------------|------------------------|
| `DB_HOST`       | `localhost`    | Database host          |
| `DB_PORT`       | `5432`         | Database port          |
| `DB_NAME`       | `simpleapp_db` | Database name          |
| `DB_USERNAME`   | `simpleapp`    | Database username      |
| `DB_PASSWORD`   | `simpleapppass`| Database password      |
| `APP_VERSION`   | `1.0.0`        | Application version    |
| `APP_ENVIRONMENT` | `local`      | Environment name       |

## Docker

### Build the image

```bash
docker build -t simpleapp-api .
```

### Run the container

```bash
docker run -p 8081:8081 \
  -e DB_HOST=host.docker.internal \
  -e DB_PORT=5432 \
  -e DB_NAME=simpleapp_db \
  -e DB_USERNAME=simpleapp \
  -e DB_PASSWORD=simpleapppass \
  simpleapp-api
```

### Docker Image Features

- Multi-stage build for optimized image size
- Non-root user execution for security
- Health check included
- Optimized JVM settings for containers:
  - Container-aware memory management
  - G1 garbage collector
  - Exit on OutOfMemoryError

## GitOps Deployment

This application is deployed using GitOps practices. The deployment manifests and configurations are maintained in the [simpleapp-gitops](https://github.com/atychkivskyy/simpleapp-gitops) repository.

### Deployment Flow

1. Changes are pushed to this repository
2. CI/CD pipeline builds and pushes a new Docker image
3. Image tag is updated in `simpleapp-gitops`
4. ArgoCD/Flux detects changes and syncs the deployment

## Testing

Run the test suite:

```bash
./mvnw test
```

The project includes:
- Unit tests for controllers, services, and models
- Integration tests for API endpoints
- Repository tests with H2 in-memory database

## Project Structure

```
simpleapp-api/
├── src/
│   ├── main/
│   │   ├── java/com/atychkivskyy/simpleapp/
│   │   │   ├── config/          # Configuration classes
│   │   │   ├── controller/      # REST controllers
│   │   │   ├── exception/       # Exception handlers
│   │   │   ├── model/           # JPA entities
│   │   │   ├── repository/      # Data repositories
│   │   │   └── service/         # Business logic
│   │   └── resources/
│   │       ├── db/migration/    # Flyway migrations
│   │       └── application.yaml # Configuration
│   └── test/                    # Test classes
├── docker-compose.yaml          # Local development setup
├── Dockerfile                   # Container image definition
└── pom.xml                      # Maven configuration
```

## Technology Stack

| Category       | Technology                    |
|----------------|-------------------------------|
| Framework      | Spring Boot 4.0.1             |
| Language       | Java 21                       |
| Build Tool     | Maven                         |
| Database       | PostgreSQL 18                 |
| ORM            | Spring Data JPA / Hibernate   |
| Migration      | Flyway                        |
| Validation     | Jakarta Validation            |
| Utilities      | Lombok                        |
| Container      | Docker (Eclipse Temurin)      |
| Monitoring     | Spring Boot Actuator          |

[//]: # (## 📄 License)

[//]: # ()
[//]: # (This project is licensed under the terms specified in the project's license file.)

## Related Repositories

- [simpleapp-gitops](https://github.com/atychkivskyy/simpleapp-gitops) - GitOps deployment manifests

