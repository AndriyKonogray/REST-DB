CREATE TABLE IF NOT EXISTS users
(
    id    BIGSERIAL PRIMARY KEY,
    full_name  VARCHAR(200),
    login VARCHAR(254) NOT NULL,
    date_of_birth DATE,
    gender VARCHAR(254)
);