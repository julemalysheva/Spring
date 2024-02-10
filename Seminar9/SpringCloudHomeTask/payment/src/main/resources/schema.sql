CREATE TABLE IF NOT EXISTS accounts (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255),
                        balance NUMERIC(10,2)
    );

CREATE TABLE IF NOT EXISTS payments (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          account_id BIGINT NOT NULL,
                          amount DECIMAL(10, 2) NOT NULL,
                          reservation_id BIGINT
);

