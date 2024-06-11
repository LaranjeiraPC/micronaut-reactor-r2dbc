CREATE SCHEMA IF NOT EXISTS overdose;

CREATE TABLE IF NOT EXISTS overdose.medicine (
    id          UUID            NOT NULL,
    name        VARCHAR(100)    NOT NULL,
    description VARCHAR(255)    NOT NULL,
    risk        INT             NOT NULL,
    date_create TIMESTAMP       NOT NULL,
    date_update TIMESTAMP       NULL)