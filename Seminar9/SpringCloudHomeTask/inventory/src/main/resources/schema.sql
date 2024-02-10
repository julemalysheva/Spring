CREATE TABLE reservations (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          product_id BIGINT NOT NULL,
                          quantity INT NOT NULL,
                          payment_id BIGINT

);
