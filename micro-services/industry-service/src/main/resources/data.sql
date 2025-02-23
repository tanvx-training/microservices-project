CREATE TABLE industries
(
    industry_id      SERIAL PRIMARY KEY,
    industry_name    VARCHAR(255) NOT NULL,
    created_on       TIMESTAMP DEFAULT now(),
    created_by       VARCHAR(255),
    last_modified_on TIMESTAMP DEFAULT now(),
    last_modified_by VARCHAR(255)
);