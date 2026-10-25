-- create Users table
CREATE TABLE users
(
    id              UUID PRIMARY KEY,
    email           VARCHAR(255)             NOT NULL UNIQUE,
    hashed_password VARCHAR(255)             NOT NULL,
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL
);