-- liquibase formatted sql

-- changeset Homychok:1
CREATE TABLE category(
                         id   BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255) UNIQUE NOT NULL
);

-- changeset Homychok:2
INSERT INTO category(name)
VALUES ('Продукты питания'),
       ('Товары для дома'),
       ('Косметика'),
       ('Одежда');