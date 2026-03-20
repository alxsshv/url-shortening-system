CREATE SCHEMA IF NOT EXISTS links;
SET search_path TO links, public;

CREATE TABLE links.links (
    id BIGINT NOT NULL PRIMARY KEY,
    token VARCHAR(10) NOT NULL,
    url VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    expired_at TIMESTAMP WITHOUT TIME ZONE
);

CREATE INDEX IF NOT EXISTS idx_links_token ON links.links(token);
