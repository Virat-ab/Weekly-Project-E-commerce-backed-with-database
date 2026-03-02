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

## API Endpoints

### Products
- `GET /api/products` - Get products with pagination and filtering
- `GET /api/products/{id}` - Get product by ID
- `POST /api/products` - Create new product (Admin only)
- `PUT /api/products/{id}` - Update product (Admin only)
- `DELETE /api/products/{id}` - Delete product (Admin only)

### Orders
- `GET /api/orders` - Get user's orders
- `GET /api/orders/{id}` - Get order details
- `POST /api/orders` - Create new order
- `PUT /api/orders/{id}/cancel` - Cancel order

### Users & Auth
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - User login
- `GET /api/users/profile` - Get user profile
- `PUT /api/users/profile` - Update profile

### Payments
- `POST /api/payments/process` - Process payment
- `POST /api/payments/{id}/refund` - Refund payment

---

## How to Run in VS Code

### Prerequisites
Install these tools before starting:

1. **Java 17** - Download from https://adoptium.net/
2. **Maven** - Download from https://maven.apache.org/download.cgi
3. **Docker Desktop** - Download from https://www.docker.com/products/docker-desktop
4. **VS Code Extensions**:
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
- Host: `localhost:5432`
- Database: `ecommerce_db`
- Username: `postgres`
- Password: `postgres`

If you want different credentials, edit `application.yml` accordingly.

#### Step 4: Run the Application

**Option A – Using VS Code Spring Boot Dashboard (Recommended)**
1. Click the **Spring Boot Dashboard** icon in the left Activity Bar
2. You'll see `EcommerceApplication` listed
3. Click the ▶ (Run) button next to it
4. Watch the output in the terminal

**Option B – Using the Run Button**
1. Open `src/main/java/com/ecommerce/EcommerceApplication.java`
2. Click **Run** (the green triangle above `main` method)
3. Or press `F5` to start with debugger

**Option C – Using Maven in Terminal**
```bash
./mvnw spring-boot:run
```
(On Windows use: `mvnw.cmd spring-boot:run`)

#### Step 5: Verify the Application Started
Look for this in the console output:
```
Started EcommerceApplication in X.XXX seconds
```

Also check:
- Flyway migrations ran: `Successfully applied 3 migrations`
- HikariCP pool started: `HikariPool-1 - Start completed`

#### Step 6: Access the API

Open your browser and go to:
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/api-docs

---

### Testing the API (Step-by-Step)

#### Register a User
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "Test@123",
    "firstName": "Test",
    "lastName": "User"
  }'
```

#### Login and Get JWT Token
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "Test@123"
  }'
```
Copy the `token` value from the response.

#### Get Products (No Auth Required)
```bash
curl http://localhost:8080/api/products
```

#### Create an Order (Auth Required)
```bash
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN_HERE" \
  -d '{
    "shippingAddress": "123 Main St, City",
    "items": [
      {"productId": 1, "quantity": 2}
    ]
  }'
```

---

### Seeded Test Data
The database comes with test data from Flyway migrations:

| Username | Password | Role |
|----------|----------|------|
| admin | password | ADMIN |
| john_doe | password | CUSTOMER |

**Note:** The seed passwords in V2 migration use BCrypt hash for `password` (the raw text).

---

### Stopping the Application
- Press `Ctrl+C` in the terminal, or
- Click the red stop button in VS Code's Spring Boot Dashboard

### Stopping Docker
```bash
docker-compose down
```
To also remove data:
```bash
docker-compose down -v
```

---

### Troubleshooting

**"Connection refused" on port 5432**
→ Docker isn't running. Start Docker Desktop and run `docker-compose up -d postgres`

**"Port 8080 already in use"**
→ Change the port in `application.yml`: `server.port: 8081`

**Maven can't download dependencies**
→ Check your internet connection. Corporate networks may need proxy settings in `~/.m2/settings.xml`

**"Could not validate Flyway migration"**
→ Drop and recreate the database:
```bash
docker-compose down -v
docker-compose up -d postgres
```

**Java not found**
→ Make sure Java 17 is installed and `JAVA_HOME` is set. Check: `java -version`
