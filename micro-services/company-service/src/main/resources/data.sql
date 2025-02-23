CREATE TABLE companies
(
    company_id       SERIAL PRIMARY KEY,
    name             VARCHAR(255) NOT NULL,
    description      TEXT,
    company_size     INTEGER,
    state            VARCHAR(50),
    country          VARCHAR(10),
    city             VARCHAR(100),
    zip_code         VARCHAR(20),
    address          TEXT,
    url              VARCHAR(255),
    created_on       TIMESTAMP DEFAULT now(),
    created_by       VARCHAR(255),
    last_modified_on TIMESTAMP DEFAULT now(),
    last_modified_by VARCHAR(255)
);

CREATE TABLE company_metrics
(
    company_metric_id SERIAL PRIMARY KEY,
    company_id        INTEGER NOT NULL REFERENCES companies (company_id) ON DELETE CASCADE,
    employee_count    INTEGER,
    follower_count    INTEGER,
    time_recorded     TIMESTAMP,
    created_on        TIMESTAMP DEFAULT now(),
    created_by        VARCHAR(255),
    last_modified_on  TIMESTAMP DEFAULT now(),
    last_modified_by  VARCHAR(255)
);

CREATE TABLE company_specialities
(
    company_id       INTEGER NOT NULL REFERENCES companies (company_id) ON DELETE CASCADE,
    speciality_id    INTEGER NOT NULL,
    PRIMARY KEY (company_id, speciality_id),
    created_on       TIMESTAMP DEFAULT now(),
    created_by       VARCHAR(255),
    last_modified_on TIMESTAMP DEFAULT now(),
    last_modified_by VARCHAR(255)
);

CREATE TABLE company_industries
(
    company_id       INTEGER NOT NULL REFERENCES companies (company_id) ON DELETE CASCADE,
    industry_id      INTEGER,
    PRIMARY KEY (company_id, industry_id),
    created_on       TIMESTAMP DEFAULT now(),
    created_by       VARCHAR(255),
    last_modified_on TIMESTAMP DEFAULT now(),
    last_modified_by VARCHAR(255)
);