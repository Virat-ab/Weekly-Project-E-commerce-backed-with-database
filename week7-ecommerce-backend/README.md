# E-Commerce Backend with Database Integration

A comprehensive e-commerce backend system using Spring Boot 3.x, Spring Data JPA, and PostgreSQL.

## Technology Stack
- Java 17
- Spring Boot 3.2
- Spring Data JPA + Hibernate
- PostgreSQL 15
- Flyway (Database migrations)
- HikariCP (Connection pooling)
- Spring Security + JWT
- Docker & Docker Compose
- Lombok + MapStruct
- SpringDoc OpenAPI (Swagger UI)

## Features
- Complete e-commerce database schema with relationships
- Product catalog with categories, inventory, and pricing
- Shopping cart and order management
- User authentication with JWT
- Payment processing system
- Database migrations with Flyway
- Transaction management for order processing
- Query optimization with indexes and caching
- Connection pooling with HikariCP
- Comprehensive API documentation (Swagger UI)



## How to Run in VS Code

### Prerequisites
Install these tools before starting:

1. **Java 17** - Download from https://adoptium.net/
2. **Docker Desktop** - Download from https://www.docker.com/products/docker-desktop
3. **VS Code Extensions**:
   - Extension Pack for Java (Microsoft)
   - Spring Boot Extension Pack (VMware)
   - Docker (Microsoft)

---

### Step-by-Step Setup in VS Code

#### Step 1: Open the Project
1. Open VS Code
2. Go to **File → Open Folder**
3. Select the `week7-ecommerce-backend` folder
4. Wait for VS Code to detect it as a Java/Maven project (bottom status bar will show Java loading)

#### Step 2: Start PostgreSQL with Docker
Open the **VS Code Terminal** (`Ctrl+` ` ` or **Terminal → New Terminal**) and run:

```bash
docker-compose up -d postgres
```

Wait for the database to start. Verify with:
```bash
docker ps
```
You should see `ecommerce-postgres` running.

#### Step 3: Configure the Database Connection
The default config in `src/main/resources/application.yml` uses:

If you want different credentials, edit `application.yml` accordingly.

#### Step 4: Run the Application

**Option A – Using VS Code Spring Boot Dashboard (Recommended)**
1. Click the **Spring Boot Dashboard** icon in the left Activity Bar
2. You'll see `EcommerceApplication` listed
3. Click the ▶ (Run) button next to it
4. Watch the output in the terminal


#### Step 5: Verify the Application Started
Look for this in the console output:
```
Started EcommerceApplication in X.XXX seconds