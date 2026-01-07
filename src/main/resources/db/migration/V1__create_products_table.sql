CREATE TABLE IF NOT EXISTS products
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    description TEXT,
    price       DECIMAL(10, 2) NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_products_name ON products (name);

INSERT INTO products (name, description, price)
VALUES ('Laptop', 'High-performance laptop', 999.99),
       ('Keyboard', 'Mechanical keyboard', 149.99),
       ('Mouse', 'Wireless mouse', 49.99);