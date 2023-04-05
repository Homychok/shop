-- liquibase formatted sql

-- changeset Homychok:1
CREATE TABLE product_categories (
                                    categories_id BIGINT NOT NULL REFERENCES category(id),
                                    product_id   BIGINT NOT NULL REFERENCES product(id),
                                    PRIMARY KEY (product_id, categories_id)
);
