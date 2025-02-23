CREATE TABLE skills (
    skill_id SERIAL PRIMARY KEY,
    skill_abr VARCHAR(50) NOT NULL,
    skill_name VARCHAR(255) NOT NULL,
    created_on TIMESTAMP DEFAULT now(),
    created_by VARCHAR(255),
    last_modified_on TIMESTAMP DEFAULT now(),
    last_modified_by VARCHAR(255)
);