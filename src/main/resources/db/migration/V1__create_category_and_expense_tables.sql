CREATE TABLE lommebok_category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    color VARCHAR(7) NOT NULL,
    CONSTRAINT uk_lommebok_category_name UNIQUE (name)
);

CREATE TABLE lommebok_expense (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    amount DECIMAL(10, 2) NOT NULL,
    spent_at DATE NOT NULL,
    category_id BIGINT NOT NULL,
    payment_type VARCHAR(20) NOT NULL,
    CONSTRAINT fk_lommebok_expense_category FOREIGN KEY (category_id) REFERENCES lommebok_category (id)
);
