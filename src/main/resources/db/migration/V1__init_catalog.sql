CREATE TABLE categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE brands (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    sku VARCHAR(50) NOT NULL UNIQUE,
    price NUMERIC(12,2) NOT NULL,
    stock INTEGER NOT NULL DEFAULT 0,
    category_id BIGINT NOT NULL,
    brand_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),

    CONSTRAINT fk_products_categories
        FOREIGN KEY (category_id)
            REFERENCES categories(id)
            ON DELETE RESTRICT,

    CONSTRAINT fk_products_brands
        FOREIGN KEY (brand_id)
            REFERENCES brands(id)
            ON DELETE RESTRICT
);