CREATE TABLE benefits
(
    benefit_id       SERIAL PRIMARY KEY,
    type             VARCHAR(255) NOT NULL,
    created_on       TIMESTAMP DEFAULT now(),
    created_by       VARCHAR(255),
    last_modified_on TIMESTAMP DEFAULT now(),
    last_modified_by VARCHAR(255)
);