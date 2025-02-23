CREATE TABLE jobs
(
    job_id                     SERIAL PRIMARY KEY,
    company_id                 INTEGER,
    company_name               VARCHAR(255) NOT NULL,
    title                      VARCHAR(255) NOT NULL,
    description                TEXT,
    max_salary                 NUMERIC,
    min_salary                 NUMERIC,
    med_salary                 NUMERIC,
    pay_period                 VARCHAR(50),
    work_type                  VARCHAR(100),
    formatted_experience_level VARCHAR(100),
    remote_allowed             BOOLEAN,
    location                   VARCHAR(255),
    zip_code                   VARCHAR(50),
    currency                   VARCHAR(10),
    compensation_type          VARCHAR(100),
    skills_desc                TEXT,
    expiry                     TIMESTAMP,
    closed_time                TIMESTAMP,
    created_on                 TIMESTAMP DEFAULT now(),
    created_by                 VARCHAR(255),
    last_modified_on           TIMESTAMP DEFAULT now(),
    last_modified_by           VARCHAR(255)
);

CREATE TABLE job_salaries
(
    salary_id         SERIAL PRIMARY KEY,
    job_id            BIGINT NOT NULL REFERENCES jobs (job_id) ON DELETE CASCADE,
    max_salary        NUMERIC(10, 2),
    med_salary        NUMERIC(10, 2),
    min_salary        NUMERIC(10, 2),
    pay_period        VARCHAR(50),
    currency          VARCHAR(10),
    compensation_type VARCHAR(50),
    created_on        TIMESTAMP DEFAULT now(),
    created_by        VARCHAR(255),
    last_modified_on  TIMESTAMP DEFAULT now(),
    last_modified_by  VARCHAR(255)
);

CREATE TABLE job_skills
(
    job_id           BIGINT NOT NULL REFERENCES jobs (job_id) ON DELETE CASCADE,
    skill_id         INTEGER,
    PRIMARY KEY (job_id, skill_id),
    created_on       TIMESTAMP DEFAULT now(),
    created_by       VARCHAR(255),
    last_modified_on TIMESTAMP DEFAULT now(),
    last_modified_by VARCHAR(255)
);

CREATE TABLE job_benefits
(
    job_id           BIGINT NOT NULL REFERENCES jobs (job_id) ON DELETE CASCADE,
    benefit_id       INTEGER,
    inferred         BOOLEAN,
    PRIMARY KEY (job_id, benefit_id),
    created_on       TIMESTAMP DEFAULT now(),
    created_by       VARCHAR(255),
    last_modified_on TIMESTAMP DEFAULT now(),
    last_modified_by VARCHAR(255)
);

CREATE TABLE job_industries
(
    job_id           BIGINT NOT NULL REFERENCES jobs (job_id) ON DELETE CASCADE,
    industry_id      INTEGER,
    PRIMARY KEY (job_id, industry_id),
    created_on       TIMESTAMP DEFAULT now(),
    created_by       VARCHAR(255),
    last_modified_on TIMESTAMP DEFAULT now(),
    last_modified_by VARCHAR(255)
);