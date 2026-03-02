-- V2__seed_data.sql
-- Seed initial data

-- Admin user (password: Admin@123)
INSERT INTO users (username, email, password, first_name, last_name, role)
VALUES (
    'admin',
    'admin@ecommerce.com',
    '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi',
    'Admin',
    'User',
    'ADMIN'
);

-- Test customer (password: User@123)
INSERT INTO users (username, email, password, first_name, last_name, role)
VALUES (
    'john_doe',
    'john@example.com',
    '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi',
    'John',
    'Doe',
    'CUSTOMER'
);

-- Categories
INSERT INTO categories (name, description, slug) VALUES
    ('Electronics', 'Electronic devices and accessories', 'electronics'),
    ('Clothing', 'Fashion and apparel', 'clothing'),
    ('Books', 'Books and literature', 'books'),
    ('Home & Garden', 'Home improvement and garden products', 'home-garden');

INSERT INTO categories (name, description, slug, parent_id) VALUES
    ('Smartphones', 'Mobile phones', 'smartphones', 1),
    ('Laptops', 'Laptop computers', 'laptops', 1),
    ('Men''s Clothing', 'Men fashion', 'mens-clothing', 2),
    ('Women''s Clothing', 'Women fashion', 'womens-clothing', 2);

-- Products
INSERT INTO products (name, description, sku, price, stock_quantity, category_id) VALUES
    ('iPhone 15 Pro', 'Latest Apple iPhone with A17 chip', 'APPLE-IP15PRO', 999.99, 50, 5),
    ('Samsung Galaxy S24', 'Samsung flagship smartphone', 'SAM-GAL-S24', 849.99, 75, 5),
    ('MacBook Pro 14', 'Apple MacBook Pro with M3 chip', 'APPLE-MBP14', 1999.99, 25, 6),
    ('Dell XPS 15', 'Dell premium laptop', 'DELL-XPS15', 1499.99, 30, 6),
    ('Spring Boot in Action', 'Learn Spring Boot development', 'BOOK-SB-ACTION', 49.99, 200, 3),
    ('Clean Code', 'A handbook of agile software craftsmanship', 'BOOK-CLEAN-CODE', 39.99, 150, 3);
