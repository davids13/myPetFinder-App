GRANT ALL PRIVILEGES ON DATABASE petfinderdb TO postgres;

DROP DATABASE IF EXISTS petfinderdb;

\c petfinderdb;

CREATE TABLE IF NOT EXISTS owners (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(255)
);

INSERT INTO owners VALUES (1, 'Joe', 'Doe', 'abc@gmail.com', '0123456789');
