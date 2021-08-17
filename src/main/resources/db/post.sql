CREATE TABLE post (
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(255),
    text    VARCHAR(255),
    link    VARCHAR(255) UNIQUE,
    created TIMESTAMP
);