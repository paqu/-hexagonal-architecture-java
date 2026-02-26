# hexagonal-architecture-java

This project was created to practice integrating Spring Boot with a hexagonal architecture.

A sample e-commerce application built with **Hexagonal Architecture** (Ports & Adapters) in Java 21 and Spring Boot.
---

## Project Structure

```
hexagonal-architecture-java/
├── model/          # Domain layer – pure business logic, no external dependencies
├── application/    # Use cases, inbound and outbound ports
├── adapter/        # REST adapters (inbound) and JPA/InMemory adapters (outbound)
└── bootstrap/      # Application entry point, e2e tests, architecture tests (ArchUnit)
```

### Module Dependency Flow

```
bootstrap → adapter → application → model
```

No inner module knows about outer modules — dependencies always point inward.

---

## Running the Application

### Requirements

- Java 21
- Maven 3.8+
- Docker (for MySQL profile and Testcontainers integration tests)

### InMemory Profile (no database required)

```bash
mvn spring-boot:run -pl bootstrap -Dspring-boot.run.profiles=InMem
```

### MySQL Profile

```bash
# Start MySQL via Docker:
docker run -d -p 3306:3306 -e MYSQL_DATABASE=shop -e MYSQL_USER=root -e MYSQL_ROOT_PASSWORD=test mysql:8.0

mvn spring-boot:run -pl bootstrap -Dspring-boot.run.profiles=mysql
```

---

## API

| Method   | Endpoint                          | Description                        |
|----------|-----------------------------------|------------------------------------|
| `GET`    | `/products?query={q}`             | Search products by name/description |
| `GET`    | `/carts/{customerId}`             | Get a customer's cart              |
| `POST`   | `/carts/{customerId}/line-items`  | Add a product to the cart          |
| `DELETE` | `/carts/{customerId}`             | Empty a customer's cart            |

### Examples

```bash
# Search for products
curl "http://localhost:8080/products?query=monitor"

# Add a product to the cart
curl -X POST "http://localhost:8080/carts/1/line-items?productId=K3SR7PBX&quantity=2"

# Get the cart
curl "http://localhost:8080/carts/1"

# Empty the cart
curl -X DELETE "http://localhost:8080/carts/1"
```

---

## Tests

```bash
# All tests (including Testcontainers – requires Docker)
mvn test

# Unit and InMemory tests only (no Docker required)
mvn test -pl model,application
mvn test -pl adapter -Dspring.profiles.active=InMem
```

### Test Levels

| Type | Module | Description |
|------|--------|-------------|
| Unit | `model`, `application` | Domain logic and services, no Spring context |
| Adapter (InMemory) | `adapter` | REST controllers and in-memory repositories |
| Integration (JPA) | `adapter` | JPA repositories with Testcontainers + MySQL |
| End-to-End | `bootstrap` | Full HTTP flow through the entire application |
| Architecture | `bootstrap` | Module dependency rules enforced via ArchUnit |

---

## Hexagonal Architecture – Concept Map

```
                        ┌────────────────────────────────┐
  REST Client  ──────►  │  AddToCartController            │  Inbound Adapter
                        │  GetCartController              │
                        └────────────┬───────────────────┘
                                     │ (inbound port)
                        ┌────────────▼───────────────────┐
                        │  AddToCartUseCase (interface)   │  Application
                        │  AddToCartService (impl)        │
                        └────────────┬───────────────────┘
                                     │ (outbound port)
                        ┌────────────▼───────────────────┐
  MySQL / H2   ◄──────  │  JpaCartRepository              │  Outbound Adapter
                        │  InMemoryCartRepository         │
                        └────────────────────────────────┘
```

---

## Demo Products

The application starts with four pre-loaded products:

| ID         | Name                             | Price (EUR) |
|------------|----------------------------------|-------------|
| `TTKQ8NJZ` | Plastic Sheeting                 | 42.99       |
| `K3SR7PBX` | 27-Inch Curved Computer Monitor  | 159.99      |
| `Q3W43CNC` | Dual Monitor Desk Mount          | 119.90      |
| `WM3BPG3E` | 50ft Led Lights                  | 11.69       |

---

## Tech Stack

- **Java 21** + **Spring Boot 3.5**
- **Spring Data JPA** + **MySQL** / **H2**
- **Lombok** – boilerplate reduction
- **Testcontainers** – integration tests against a real database
- **REST Assured** – API testing
- **ArchUnit** – enforcing architectural dependency rules
