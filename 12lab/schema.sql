CREATE TABLE IF NOT EXISTS clothes_item (
    id SERIAL PRIMARY KEY,
    type VARCHAR(20) NOT NULL,
    name VARCHAR(100) NOT NULL,
    brand VARCHAR(100) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    size VARCHAR(10),
    color VARCHAR(50),
    extra1 VARCHAR(100),
    extra2 VARCHAR(100)
);
