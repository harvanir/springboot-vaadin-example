DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL,
    last_name  VARCHAR(250) NOT NULL,
    created_date DATE,
    updated_date DATE,
    version BIGINT NOT NULL DEFAULT 0
);