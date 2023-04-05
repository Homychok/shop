-- liquibase formatted sql

-- changeset Homychok:1
create table product
(
    id             BIGSERIAL PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    description    VARCHAR(1000) NOT NULL,
    create_date   TIMESTAMP NOT NULL,
    modificate_date   TIMESTAMP NOT NULL
);

