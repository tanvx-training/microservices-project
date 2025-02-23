CREATE TABLE job_postings
(
    posting_id           BIGINT PRIMARY KEY,
    job_id               BIGINT NOT NULL,
    company_name         VARCHAR(255),
    views                INTEGER,
    applies              INTEGER,
    original_listed_time TIMESTAMP,
    listed_time          TIMESTAMP,
    posting_domain       VARCHAR(255),
    job_posting_url      TEXT,
    application_url      TEXT,
    application_type     VARCHAR(100),
    sponsored            BOOLEAN,
    normalized_salary    NUMERIC,
    fips                 VARCHAR(50)
);